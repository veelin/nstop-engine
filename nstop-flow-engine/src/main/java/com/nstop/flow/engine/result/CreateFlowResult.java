package com.nstop.flow.engine.result;

import com.google.common.base.MoreObjects;

public class CreateFlowResult extends CommonResult {
    private String flowModuleId;

    public String getFlowModuleId() {
        return flowModuleId;
    }

    public void setFlowModuleId(String flowModuleId) {
        this.flowModuleId = flowModuleId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getCode())
                .add("errMsg", getMessage())
                .add("flowModuleId", flowModuleId)
                .toString();
    }
}
