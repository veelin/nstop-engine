package com.nstop.data.source.enums;


import com.nstop.data.source.constant.DataSourceConstant;
import com.nstop.data.source.model.property.MySqlSource;
import com.nstop.data.source.model.property.SourceProperty;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author: origindoris
 * @Title: SourceTypeEnum
 * @Description:
 * @date: 2022/10/18 10:20
 */
public enum SourceTypeEnum {
    /**
     * 数据源类型
     */
    MYSQL(DataSourceConstant.MYSQL, "mysql", MySqlSource.class),;
    private String type;

    private String desc;

    private Class<? extends SourceProperty> typeClass;


    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }


    public Class<? extends SourceProperty> getTypeClass() {
        return typeClass;
    }

    SourceTypeEnum(String type, String desc, Class<? extends SourceProperty> typeClass) {
        this.type = type;
        this.desc = desc;
        this.typeClass = typeClass;
    }


    public static SourceTypeEnum getSourceType(String code){
        Optional<SourceTypeEnum> any = Arrays.stream(SourceTypeEnum.values()).filter(numberFormat -> code.equals(numberFormat.getType())).findAny();
        if (any.isEmpty()) {
            return null;
        }
        return any.get();
    }
}
