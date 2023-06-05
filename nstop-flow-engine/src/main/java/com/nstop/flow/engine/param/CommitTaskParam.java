package com.nstop.flow.engine.param;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.MoreObjects;

import java.util.List;

public class CommitTaskParam extends RuntimeTaskParam {
    private JSONObject variables;

    public JSONObject getVariables() {
        return variables;
    }

    public void setVariables(JSONObject variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("flowInstanceId", getFlowInstanceId())
                .add("taskInstanceId", getTaskInstanceId())
                .add("variables", variables)
                .toString();
    }
}
