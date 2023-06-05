package com.nstop.biz.controller.pojo.request;


import com.nstop.biz.test.Constant;
import com.nstop.flow.engine.param.CreateFlowParam;

/**
 * @Author: james zhangxiao
 * @Date: 4/1/22
 * @Description:
 */
public class CreateFlowRequest extends CreateFlowParam {

    public CreateFlowRequest() {
        super(Constant.tenant, Constant.caller);
    }

}
