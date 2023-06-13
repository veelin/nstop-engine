package com.nstop.flow.engine.param;

import com.google.common.base.MoreObjects;

public class GetFlowModuleParam {
    private String flowModuleId;
    private String flowDeployId;
    private String flowType;
    private String flowKey;

    private boolean isDebug;

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

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("flowModuleId", flowModuleId)
                .add("flowDeployId", flowDeployId)
                .toString();
    }


}
