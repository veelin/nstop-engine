package com.nstop.flow.engine.exception;

import com.nstop.flow.engine.common.ErrorEnum;

public class ParamException extends TurboException {

    public ParamException(int errNo, String errMsg) {
        super(errNo, errMsg);
    }

    public ParamException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}

