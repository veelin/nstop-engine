package com.nstop.flow.engine.result;

import com.alibaba.fastjson.JSONObject;
import com.nstop.flow.engine.bo.NodeInstance;
import com.nstop.flow.engine.common.ErrorEnum;
import com.google.common.base.MoreObjects;

import java.util.List;

public class RuntimeResult extends CommonResult {
    private String flowInstanceId;
    private int status;
    private NodeInstance activeTaskInstance;
    private JSONObject variables;

    public RuntimeResult() {
        super();
    }

    public RuntimeResult(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public String getFlowInstanceId() {
        return flowInstanceId;
    }

    public void setFlowInstanceId(String flowInstanceId) {
        this.flowInstanceId = flowInstanceId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public NodeInstance getActiveTaskInstance() {
        return activeTaskInstance;
    }

    public void setActiveTaskInstance(NodeInstance activeTaskInstance) {
        this.activeTaskInstance = activeTaskInstance;
    }

    public JSONObject getVariables() {
        return variables;
    }

    public void setVariables(JSONObject variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getCode())
                .add("errMsg", getMessage())
                .add("flowInstanceId", flowInstanceId)
                .add("status", status)
                .add("activeTaskInstance", activeTaskInstance)
                .add("variables", variables)
                .toString();
    }
}
