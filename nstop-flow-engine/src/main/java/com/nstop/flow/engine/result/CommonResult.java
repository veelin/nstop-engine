package com.nstop.flow.engine.result;

import com.nstop.flow.engine.common.ErrorEnum;
import com.google.common.base.MoreObjects;

public class CommonResult {

    private int code;
    private String message;

    public CommonResult() {
        super();
    }

    public CommonResult(ErrorEnum errorEnum) {
        this.code = errorEnum.getErrNo();
        this.message = errorEnum.getErrMsg();
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("message", message)
                .toString();
    }
}
