package com.nstop.common.model;

import com.nstop.common.enums.ErrorCode;
import lombok.Data;

/**
 * @author: origindoris
 * @Title: Result
 * @Description:
 * @date: 2022/11/15 16:07
 */
@Data
public class Result<T> {
    private Integer code;

    private String message;

    private T data;


    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setData(data);
        return result;
    }


    public static <T> Result<T> failure(int code, String message) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> failure(ErrorCode errorCode) {
        Result<T> result = new Result<T>();
        result.setCode(errorCode.getCode());
        result.setMessage(errorCode.getMsg());
        return result;
    }

}

