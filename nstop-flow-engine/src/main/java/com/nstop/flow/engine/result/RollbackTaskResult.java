package com.nstop.flow.engine.result;

import com.google.common.base.MoreObjects;

public class RollbackTaskResult extends RuntimeResult {
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getCode())
                .add("errMsg", getMessage())
                .toString();
    }
}
