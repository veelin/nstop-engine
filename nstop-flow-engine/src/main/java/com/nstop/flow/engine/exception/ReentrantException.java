package com.nstop.flow.engine.exception;

import com.nstop.flow.engine.common.ErrorEnum;

public class ReentrantException extends ProcessException {

    public ReentrantException(int errNo, String errMsg) {
        super(errNo, errMsg);
    }

    public ReentrantException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
