package com.nstop.flow.engine.exception;

import com.nstop.flow.engine.common.ErrorEnum;

public class DefinitionException extends TurboException {

    public DefinitionException(int errNo, String errMsg) {
        super(errNo, errMsg);
    }

    public DefinitionException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
