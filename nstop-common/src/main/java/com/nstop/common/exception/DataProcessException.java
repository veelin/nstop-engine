package com.nstop.common.exception;


import com.nstop.common.enums.ErrorCode;

/**
 * @author: origindoris
 * @Title: ProcessException
 * @Description:
 * @date: 2022/10/18 15:48
 */
public class DataProcessException extends NStopException {
    public DataProcessException(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public DataProcessException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DataProcessException(ErrorCode errorCode, String detailMsg) {
        super(errorCode, detailMsg);
    }
}
