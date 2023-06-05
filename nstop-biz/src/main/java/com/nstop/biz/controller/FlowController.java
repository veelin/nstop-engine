package com.nstop.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nstop.biz.controller.pojo.request.*;
import com.nstop.biz.controller.pojo.response.*;
import com.nstop.biz.test.LeaveServiceImpl;
import com.nstop.flow.engine.common.ErrorEnum;
import com.nstop.flow.engine.common.FlowTypeEnum;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.engine.TurboProcessEngine;
import com.nstop.flow.engine.param.GetFlowModuleParam;
import com.nstop.flow.engine.param.StartProcessParam;
import com.nstop.flow.engine.result.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: james zhangxiao
 * @Date: 4/1/22
 * @Description: logigcFlow与turbo交互样例
 */
@RestController
@RequestMapping("/n_stop")
public class FlowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowController.class);

    @Resource
    private FlowServiceImpl logicFlowService;
    @Resource
    private TurboProcessEngine turboProcessEngine;

    /**
     * 创建流程
     *
     * @param createFlowParam
     * @return
     */
    @RequestMapping(value = "/createFlow", method = {RequestMethod.POST})
    public BaseResponse<CreateFlowResponse> createFlow(@RequestBody CreateFlowRequest createFlowParam) {
        try {
            CreateFlowResult createFlowResult = logicFlowService.createFlow(createFlowParam);
            CreateFlowResponse createFlowResponse = new CreateFlowResponse();
            BaseResponse<CreateFlowResponse> baseResponse = BaseResponse.make(createFlowResponse);
            BeanUtils.copyProperties(createFlowResult, baseResponse);
            BeanUtils.copyProperties(createFlowResult, createFlowResponse);
            return baseResponse;
        } catch (Exception e) {
            LOGGER.error("createFlow error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 保存流程模型数据
     *
     * @param updateFlowRequest flowModuleId 使用createFlow返回的flowModuleId
     * @return 成功 or 失败
     */
    @RequestMapping(value = "/saveFlowModel", method = {RequestMethod.POST})
    public BaseResponse<String> saveFlowModel(@RequestBody UpdateFlowRequest updateFlowRequest) {
        try {
            UpdateFlowResult updateFlowResult = logicFlowService.updateFlow(updateFlowRequest);
            BaseResponse<String> baseResponse = new BaseResponse<>(ErrorEnum.SUCCESS);
            BeanUtils.copyProperties(updateFlowResult, baseResponse);
            return baseResponse;
        } catch (Exception e) {
            LOGGER.error("saveFlowModel error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 发布流程
     *
     * @param deployFlowRequest flowModuleId 使用createFlow返回的flowModuleId
     * @return
     */
    @RequestMapping(value = "/deployFlow", method = {RequestMethod.POST})
    public BaseResponse<DeployFlowResponse> deployFlow(@RequestBody DeployFlowRequest deployFlowRequest) {
        try {
            DeployFlowResult deployFlowResult = logicFlowService.deployFlow(deployFlowRequest);
            DeployFlowResponse deployFlowResponse = new DeployFlowResponse();
            BaseResponse<DeployFlowResponse> baseResponse = BaseResponse.make(deployFlowResponse);
            BeanUtils.copyProperties(deployFlowResult, baseResponse);
            BeanUtils.copyProperties(deployFlowResult, deployFlowResponse);
            return baseResponse;
        } catch (Exception e) {
            LOGGER.error("deployFlow error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }


    /**
     * 查询流程
     *
     * @param getFlowModuleRequest 单个流程请求参数
     * @return 单个流程数据
     */
    @RequestMapping(value = "/queryFlow", method = {RequestMethod.POST})
    public BaseResponse<GetFlowModuleResponse> queryFlow(@RequestBody GetFlowModuleRequest getFlowModuleRequest) {
        try {
            FlowModuleResult flowModuleResult = logicFlowService.getFlowModule(getFlowModuleRequest);
            GetFlowModuleResponse getFlowModuleResponse = new GetFlowModuleResponse();
            BaseResponse<GetFlowModuleResponse> baseResponse = BaseResponse.make(getFlowModuleResponse);
            BeanUtils.copyProperties(flowModuleResult, baseResponse);
            BeanUtils.copyProperties(flowModuleResult, getFlowModuleResponse);
            return baseResponse;
        } catch (Exception e) {
            LOGGER.error("queryFlow error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }


    /**
     * 分页查询流程列表
     *
     * @param getFlowModuleListRequest 分页请求参数
     * @return 返回列表数据
     */
    @RequestMapping(value = "/queryFlowList", method = {RequestMethod.POST})
    public BaseResponse<FlowModuleListResponse> queryFlowList(@RequestBody GetFlowModuleListRequest getFlowModuleListRequest) {
        try {
            FlowModuleListResponse flowModuleListResponse = logicFlowService.getFlowModuleList(getFlowModuleListRequest);
            BaseResponse<FlowModuleListResponse> baseResponse = BaseResponse.make(flowModuleListResponse);
            baseResponse.setCode(ErrorEnum.SUCCESS.getErrNo());
            baseResponse.setMessage(ErrorEnum.SUCCESS.getErrMsg());
            return baseResponse;
        } catch (Exception e) {
            LOGGER.error(" queryFlowList error", e);
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/m/{path}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse doModularityGet(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable String path) {
        return null;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/m/{path}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doModularityPost(HttpServletRequest request) throws IOException {
        String path = getFlowPath(request.getRequestURI());
        FlowModuleResult flowModule = getFlowModuleByPath(path, false);
        if (flowModule == null) {
            return new BaseResponse<>(ErrorEnum.FLOW_NOT_EXIST);
        }
        StartProcessParam startProcessParam = new StartProcessParam();
        startProcessParam.setFlowModuleId(flowModule.getFlowModuleId());
        String requestBodyString = getRequestBody(request);
        startProcessParam.setVariables(JSON.parseObject(requestBodyString));
        StartProcessResult startProcessResult = turboProcessEngine.startProcess(startProcessParam);
        if (startProcessResult.getCode() != ErrorEnum.SUCCESS.getErrNo()) {
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
        return BaseResponse.make(startProcessResult.getActiveTaskInstance().getProperties());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/debug/m/**", method = RequestMethod.POST)
    @ResponseBody
    public DebugResult debugModularity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = getFlowPath(request.getRequestURI());
        FlowModuleResult flowModule = getFlowModuleByPath(path, true);
        StartProcessParam startProcessParam = new StartProcessParam();
        startProcessParam.setFlowModuleId(flowModule.getFlowModuleId());
        String requestBodyString = getRequestBody(request);
        startProcessParam.setVariables(JSON.parseObject(requestBodyString));
        DebugResult debugResult = turboProcessEngine.debugProcess(startProcessParam);
        return debugResult;
    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test() {
        System.out.println(2);
        StartProcessParam startProcessParam = new StartProcessParam();
        startProcessParam.setFlowModuleId("ed3f6e39-ebd2-11ed-b1db-e6fa2da94e99");
//        List<InstanceData> variables = new ArrayList<>();
        StartProcessResult startProcessResult = turboProcessEngine.startProcess(startProcessParam);
        return "1";
    }


    private FlowModuleResult getFlowModuleByPath(String path, boolean isDebug) {
        GetFlowModuleParam param = new GetFlowModuleParam();
        param.setFlowType(FlowTypeEnum.http.name());
        param.setFlowKey(path);
        param.setDebug(isDebug);
        FlowModuleResult flowModule = turboProcessEngine.getFlowModule(param);
        return flowModule;
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return requestBody.toString();
    }

    private String getFlowPath(String requestURI) {
        int flag = requestURI.indexOf("/m/");
        if (flag > -1) {
            return requestURI.substring(flag + 2);
        }
        return requestURI;
    }
}
