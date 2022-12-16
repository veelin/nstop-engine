package com.nstop.data.source.service;

import com.alibaba.fastjson2.JSONObject;
import com.nstop.data.source.common.annotation.PageEnhance;
import com.nstop.common.enums.ErrorCode;
import com.nstop.common.exception.DataProcessException;
import com.nstop.common.exception.DataSourceException;
import com.nstop.common.interceptor.UserInfoThreadLocal;
import com.nstop.common.model.UserInfo;
import com.nstop.common.util.StrongUuidGenerator;
import com.nstop.data.source.constant.DataSourceConstant;
import com.nstop.data.source.context.DataSourceContext;
import com.nstop.data.source.enums.SourceTypeEnum;
import com.nstop.data.source.factory.DynamicDataSourceFactory;
import com.nstop.data.source.model.Source;
import com.nstop.data.source.model.SourceType;
import com.nstop.data.source.model.dto.SourceDTO;
import com.nstop.data.source.model.query.SourceQuery;
import com.nstop.data.source.repository.DataSourceRepository;
import com.nstop.data.source.util.SourceBeanUtil;
import com.nstop.data.source.util.SourcePropertyUtil;
import com.nstop.data.source.util.SourceSpecificationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author: origindoris
 * @Title: DataSourceService
 * @Description:
 * @date: 2022/10/18 14:38
 */
@Service
@Slf4j
@Valid
@Validated
public class DataSourceService {


    private final DataSourceRepository dataSourceRepository;

    private final EntityManager entityManager;

    private static final StrongUuidGenerator IdGenerator = new StrongUuidGenerator();

    public DataSourceService(@Lazy DataSourceRepository dataSourceRepository, EntityManager entityManager) {
        this.dataSourceRepository = dataSourceRepository;
        this.entityManager = entityManager;
    }

    public List<Source> queryAllByType(@NotBlank(message = "数据源类型不能为空！") String sourceType) {
       return dataSourceRepository.findAll((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DataSourceConstant.SOURCE_TYPE), sourceType));
    }


    /**
     * 执行本地查询，支持切换数据源
     * @param sql sql 语句
     * @param dataSourceKey 数据源key
     * @return
     * @throws DataProcessException
     * @throws DataSourceException
     */
    public List<Map> execNativeQuery(String sql, String dataSourceKey) throws DataProcessException, DataSourceException {
        if (!DynamicDataSourceFactory.getDataSourceMap().containsKey(dataSourceKey)) {
            Optional<Source> sourceDetail = dataSourceRepository.findOne((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DataSourceConstant.SOURCE_CODE), dataSourceKey));
            if (sourceDetail.isEmpty()) {
                throw new DataProcessException(ErrorCode.PROCESS_DATA_SOURCE_NOT_EXIST);
            }
            DynamicDataSourceFactory.addDataSource(sourceDetail.get());
        }
        DataSourceContext.setDataSource(dataSourceKey);
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List resultList = nativeQuery.getResultList();
        DataSourceContext.clearDataSourceKey();
        List<Map> maps = new ArrayList<>();
        for (Object o : resultList) {
            maps.add(new org.apache.commons.beanutils.BeanMap(o));
        }
        return maps;
    }


    /**
     * 保存数据源
     *
     * @param source
     * @return
     */
    @Transactional
    public Source save(@Valid @NotNull(message = "数据源信息不能为空！") Source source) throws DataSourceException {
        UserInfo userInfo = UserInfoThreadLocal.get();
        JSONObject sourceProperty = source.getSourceProperty();
        source.setSourceProperty(SourcePropertyUtil.verify(sourceProperty));
        if (StringUtils.isBlank(source.getSourceCode())) {
            source.setSourceCode(IdGenerator.getNextId());
        }
        source.setCreator(userInfo.getEmpId());
        source.setCreatorName(userInfo.getNick());
        source.setTenantCode(userInfo.getTenantCode());
        source.setDeleteFlag(false);
        source.setGmtCreate(new Date());
        Source result = dataSourceRepository.save(source);
        DynamicDataSourceFactory.addDataSource(result);
        return result;
    }

    public boolean remove(@NotNull(message = "数据源Id不能为空！") Long id){
        dataSourceRepository.deleteById(id);
        return true;
    }


    @PageEnhance
    public Page<SourceDTO> queryList(SourceQuery sourceQuery) {
        Pageable pageable = PageRequest.of(sourceQuery.getPage(), sourceQuery.getSize(), Sort.by(Sort.Direction.DESC, DataSourceConstant.ID));
        Page<Source> dataPage = dataSourceRepository.findAll(SourceSpecificationUtil.buildSpecification(sourceQuery), pageable);
        return new PageImpl<>(SourceBeanUtil.buildSourceDTO(dataPage.getContent()), dataPage.getPageable(), dataPage.getTotalElements());
    }

    public Source detailByCode(@NotBlank(message = "数据源代码不能为空！") String sourceCode){
        Optional<Source> optional = dataSourceRepository.findOne((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DataSourceConstant.SOURCE_CODE), sourceCode));
        return optional.orElse(null);
    }

    public List<SourceType> getSourceType() {
        List<SourceType> sourceTypes = new ArrayList<>();
        for (SourceTypeEnum value : SourceTypeEnum.values()) {
            SourceType sourceType = new SourceType();
            sourceType.setDesc(value.getDesc());
            sourceType.setType(value.getType());
            sourceTypes.add(sourceType);
        }
        return sourceTypes;
    }

}
