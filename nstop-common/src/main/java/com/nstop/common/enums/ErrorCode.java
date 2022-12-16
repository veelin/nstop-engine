package com.nstop.common.enums;

/**
 * @author: origindoris
 * @Title: ErrorCode
 * @Description:
 * @date: 2022/10/18 11:22
 */
public enum ErrorCode {
    /**
     * 200 SUCCESS
     */
    SUCCESS(200, "Success"),

    /**
     * 1000 ~ 1999 数据源操作异常
     */
    DATA_SOURCE_PROPERTY_TYPE_IS_NULL(1000,"sourceProperty type is null!"),
    DATA_SOURCE_PROPERTY_TYPE_CLASS_NOT_EXIST(1001,"sourceProperty type class not exist!"),
    DEFAULT_DATA_SOURCE_IS_NULL(1002,"default data source is null !"),

    /**
     * 2000 ~ 2999 流程执行异常
     */
    PROCESS_DATA_SOURCE_NOT_EXIST(2000,"process data source not exist!"),




    ;
    private Integer code;

    private String msg;


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
