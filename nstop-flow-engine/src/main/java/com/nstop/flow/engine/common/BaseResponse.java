package com.nstop.flow.engine.common;



/**
 * @Author: james zhangxiao
 * @Date: 4/6/22
 * @Description:
 */
public class BaseResponse<T> {

    int code;
    String message;
    T data;


    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(ErrorEnum errorEnum) {
        this.code = errorEnum.getErrNo();
        this.message = errorEnum.getErrMsg();
    }

    public static <T> BaseResponse<T> make(T data) {
        return (new BaseResponse<T>(ErrorEnum.SUCCESS)).setData(data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
