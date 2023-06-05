package com.nstop.flow.engine.result;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.MoreObjects;
import com.nstop.flow.engine.bo.NodeInstance;
import com.nstop.flow.engine.common.ErrorEnum;

public class HttpRuntimeResult extends CommonResult {

    private JSONObject data;

    public HttpRuntimeResult() {
        super();
    }

    public HttpRuntimeResult(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getCode())
                .add("errMsg", getMessage())
                .toString();
    }
}
