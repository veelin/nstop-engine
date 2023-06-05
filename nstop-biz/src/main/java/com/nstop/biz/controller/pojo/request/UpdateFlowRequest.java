package com.nstop.biz.controller.pojo.request;


import com.nstop.biz.test.Constant;
import com.nstop.flow.engine.param.UpdateFlowParam;

/**
 * @Author: james zhangxiao
 * @Date: 4/1/22
 * @Description:
 */
public class UpdateFlowRequest extends UpdateFlowParam {

    public UpdateFlowRequest() {
        super(Constant.tenant, Constant.caller);
    }
}
