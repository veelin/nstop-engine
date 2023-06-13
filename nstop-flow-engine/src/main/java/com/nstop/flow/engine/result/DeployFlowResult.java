package com.nstop.flow.engine.result;

import com.google.common.base.MoreObjects;

public class DeployFlowResult extends CommonResult {
    private String flowModuleId;
    private String flowDeployId;

    public String getFlowModuleId() {
        return flowModuleId;
    }

    public void setFlowModuleId(String flowModuleId) {
        this.flowModuleId = flowModuleId;
    }

    public String getFlowDeployId() {
        return flowDeployId;
    }

    public void setFlowDeployId(String flowDeployId) {
        this.flowDeployId = flowDeployId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getCode())
                .add("errMsg", getMessage())
                .add("flowModuleId", flowModuleId)
                .add("flowDeployId", flowDeployId)
                .toString();
    }
}
