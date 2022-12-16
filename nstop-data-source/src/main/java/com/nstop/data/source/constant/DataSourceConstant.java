package com.nstop.data.source.constant;


import com.nstop.data.source.common.constant.DeleteFlagConstant;

/**
 * @author: origindoris
 * @Title: DataSourceConstant
 * @Description:
 * @date: 2022/10/18 11:18
 */
public class DataSourceConstant {

    public static final String SOURCE_TABLE_NAME = "origin_data_data_source";

    public static final String DELETE_SQL = "UPDATE " + DataSourceConstant.SOURCE_TABLE_NAME + " SET delete_flag = '" + DeleteFlagConstant.YES + "' where id = ?";
    public static final String SOURCE_PROPERTY_TYPE = "type";

    public static final String SOURCE_TYPE = "sourceType";

    public static final String SOURCE_NAME = "sourceName";

    public static final String ID = "id";

    public static final String SOURCE_CODE = "sourceCode";
    public static final String DEFAULT_DATA_SOURCE_KEY = "default";



    public static final String MYSQL = "mysql";
}
