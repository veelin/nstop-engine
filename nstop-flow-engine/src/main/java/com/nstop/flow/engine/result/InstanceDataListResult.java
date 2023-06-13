package com.nstop.flow.engine.result;

import com.alibaba.fastjson.JSONObject;
import com.nstop.flow.engine.common.ErrorEnum;
import com.google.common.base.MoreObjects;

import java.util.List;

public class InstanceDataListResult extends CommonResult {
    private JSONObject variables;

    public InstanceDataListResult(ErrorEnum errorEnum) {
        super(errorEnum);
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
                .add("variables", variables)
                .toString();
    }
}
