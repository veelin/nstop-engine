package com.nstop.flow.engine.result;

import com.nstop.flow.engine.common.ErrorEnum;
import com.google.common.base.MoreObjects;

public class TerminateResult extends RuntimeResult {

    public TerminateResult(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getErrCode())
                .add("errMsg", getErrMsg())
                .toString();
    }
}
