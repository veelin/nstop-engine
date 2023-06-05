package com.nstop.biz.controller.pojo.request;


import com.nstop.biz.test.Constant;
import com.nstop.flow.engine.param.DeployFlowParam;

/**
 * @Author: james zhangxiao
 * @Date: 4/1/22
 * @Description:
 */
public class DeployFlowRequest extends DeployFlowParam {

    public DeployFlowRequest() {
        super(Constant.tenant, Constant.caller);
    }
}
