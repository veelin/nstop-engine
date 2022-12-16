package com.nstop.data.source.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.nstop.common.enums.ErrorCode;
import com.nstop.common.exception.DataSourceException;
import com.nstop.data.source.constant.DataSourceConstant;
import com.nstop.data.source.enums.SourceTypeEnum;
import com.nstop.data.source.model.property.SourceProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;


/**
 * @author: origindoris
 * @Title: SourcePropertyUtil
 * @Description:
 * @date: 2022/10/18 11:17
 */
@Slf4j
public class SourcePropertyUtil {


    public static JSONObject verify(JSONObject jsonObject) throws DataSourceException {
        SourceProperty convert = convert(jsonObject);
        convert.verifyParam();
        return JSON.parseObject(JSON.toJSONString(convert));
    }

    public static SourceProperty convert(JSONObject jsonObject) throws DataSourceException {
        String type = jsonObject.getString(DataSourceConstant.SOURCE_PROPERTY_TYPE);
        if (type == null) {
            log.info("SourcePropertyUtil.convert , SOURCE_PROPERTY_TYPE is null");
            throw new DataSourceException(ErrorCode.DATA_SOURCE_PROPERTY_TYPE_IS_NULL);
        }

        Class<? extends SourceProperty> sourceClass = getSourceClass(type);
        return JSON.parseObject(JSON.toJSONString(jsonObject), sourceClass);
    }

    public static Class<? extends SourceProperty> getSourceClass(String type) throws DataSourceException {
        if (StringUtils.isBlank(type)) {
            log.info("SourcePropertyUtil.getSourceClass , SOURCE_PROPERTY_TYPE is null");
            throw new DataSourceException(ErrorCode.DATA_SOURCE_PROPERTY_TYPE_IS_NULL);
        }
        Optional<SourceTypeEnum> any = Arrays.stream(SourceTypeEnum.values()).filter(sourceTypeEnum -> sourceTypeEnum.getType().equals(type)).findAny();
        if (any.isEmpty()) {
            log.info("SourcePropertyUtil.getSourceClass , SOURCE_PROPERTY_TYPE not exist！");
            throw new DataSourceException(ErrorCode.DATA_SOURCE_PROPERTY_TYPE_CLASS_NOT_EXIST);
        }
        return any.get().getTypeClass();
    }
}
