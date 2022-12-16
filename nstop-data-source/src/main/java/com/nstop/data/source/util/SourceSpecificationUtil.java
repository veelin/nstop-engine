package com.nstop.data.source.util;

import com.nstop.data.source.constant.DataSourceConstant;
import com.nstop.data.source.model.Source;
import com.nstop.data.source.model.query.SourceQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: origindoris
 * @Title: SourceSpecificationUtil
 * @Description:
 * @date: 2022/11/29 15:28
 */
public class SourceSpecificationUtil {
    public static Specification<Source> buildSpecification(SourceQuery sourceQuery){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesAnd = new ArrayList<>();
            List<Predicate> predicatesOr = new ArrayList<>();
            if(StringUtils.isNotBlank(sourceQuery.getSourceType())){
                predicatesAnd.add(criteriaBuilder.equal(root.get(DataSourceConstant.SOURCE_TYPE).as(String.class), sourceQuery.getSourceType()));
            }
            if(StringUtils.isNotBlank(sourceQuery.getSourceName())){
                Predicate likeNickName = criteriaBuilder.like(root.get(DataSourceConstant.SOURCE_NAME).as(String.class), "%" + sourceQuery.getSourceName() + "%");
                Predicate likeSourceCode = criteriaBuilder.like(root.get(DataSourceConstant.SOURCE_CODE).as(String.class), "%" + sourceQuery.getSourceName() + "%");
                predicatesOr.add(likeNickName);
                predicatesOr.add(likeSourceCode);
            }
            if (!predicatesAnd.isEmpty()) {
                Predicate and = criteriaBuilder.and(predicatesAnd.toArray(new Predicate[0]));
                query.where(and);
            }

            if (!predicatesOr.isEmpty()) {
                Predicate or = criteriaBuilder.or(predicatesOr.toArray(new Predicate[0]));
                query.where(or);
            }
            return query.getRestriction();
        };
    }
}
