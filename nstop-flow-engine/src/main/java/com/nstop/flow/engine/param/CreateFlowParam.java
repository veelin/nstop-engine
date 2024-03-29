package com.nstop.flow.engine.param;

import com.google.common.base.MoreObjects;

public class CreateFlowParam extends OperationParam {
    private String flowKey;
    private String flowName;
    private String remark;
    private String flowType;

    public CreateFlowParam(String tenant, String caller) {
        super(tenant, caller);
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("tenant", getTenant())
                .add("caller", getCaller())
                .add("operator", getOperator())
                .add("flowKey", flowKey)
                .add("flowName", flowName)
                .add("remark", remark)
                .toString();
    }
}
