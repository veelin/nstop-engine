package com.nstop.flow.engine.result;

import com.google.common.base.MoreObjects;
import com.nstop.flow.engine.exception.TurboException;

public class DebugResult extends RuntimeResult {
    private String flowDeployId;
    private String flowModuleId;

    private Exception exception;

    public String getFlowDeployId() {
        return flowDeployId;
    }

    public void setFlowDeployId(String flowDeployId) {
        this.flowDeployId = flowDeployId;
    }

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
                .add("flowDeployId", flowDeployId)
                .add("flowModuleId", flowModuleId)
                .toString();
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
