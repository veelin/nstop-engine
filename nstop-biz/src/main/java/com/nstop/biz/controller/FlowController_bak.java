//package com.nstop.biz.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.nstop.biz.module.pojo.request.*;
//import com.nstop.biz.module.pojo.response.CreateFlowResponse;
//import com.nstop.biz.module.pojo.response.DeployFlowResponse;
//import com.nstop.biz.module.pojo.response.FlowModuleListResponse;
//import com.nstop.biz.module.pojo.response.GetFlowModuleResponse;
//import com.nstop.datasource.rdb.RdBSqlExecutor;
//import com.nstop.flow.engine.common.BaseResponse;
//import com.nstop.flow.engine.common.Constants;
//import com.nstop.flow.engine.common.ErrorEnum;
//import com.nstop.flow.engine.common.FlowTypeEnum;
//import com.nstop.flow.engine.engine.TurboProcessEngine;
//import com.nstop.flow.engine.param.GetFlowModuleParam;
//import com.nstop.flow.engine.param.StartProcessParam;
//import com.nstop.flow.engine.result.*;
//import org.apache.commons.collections.CollectionUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * @Author: james zhangxiao
// * @Date: 4/1/22
// * @Description: logigcFlow与turbo交互样例
// */
//@RestController
//@RequestMapping("/n_stop")
//public class FlowController_bak {
//    private static final Logger LOGGER = LoggerFactory.getLogger(FlowController_bak.class);
//
//    @Resource
//    private FlowServiceImpl logicFlowService;
//    @Resource
//    private TurboProcessEngine turboProcessEngine;
//
//    @Resource
//    private RdBSqlExecutor rdBSqlExecutor;
//
//    /**
//     * 创建流程
//     *
//     * @param createFlowParam
//     * @return
//     */
//    @RequestMapping(value = "/createFlow", method = {RequestMethod.POST})
//    public BaseResponse<CreateFlowResponse> createFlow(@RequestBody CreateFlowRequest createFlowParam) {
//        try {
//            CreateFlowResult createFlowResult = logicFlowService.createFlow(createFlowParam);
//            CreateFlowResponse createFlowResponse = new CreateFlowResponse();
//            BaseResponse<CreateFlowResponse> baseResponse = BaseResponse.make(createFlowResponse);
//            BeanUtils.copyProperties(createFlowResult, baseResponse);
//            BeanUtils.copyProperties(createFlowResult, createFlowResponse);
//            return baseResponse;
//        } catch (Exception e) {
//            LOGGER.error("createFlow error", e);
//            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
//        }
//    }
//
//    /**
//     * 保存流程模型数据
//     *
//     * @param updateFlowRequest flowModuleId 使用createFlow返回的flowModuleId
//     * @return 成功 or 失败
//     */
//    @RequestMapping(value = "/saveFlowModel", method = {RequestMethod.POST})
//    public BaseResponse<String> saveFlowModel(@RequestBody UpdateFlowRequest updateFlowRequest) {
//        try {
//            UpdateFlowResult updateFlowResult = logicFlowService.updateFlow(updateFlowRequest);
//            BaseResponse<String> baseResponse = new BaseResponse<>(ErrorEnum.SUCCESS);
//            BeanUtils.copyProperties(updateFlowResult, baseResponse);
//            return baseResponse;
//        } catch (Exception e) {
//            LOGGER.error("saveFlowModel error", e);
//            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
//        }
//    }
//
//    /**
//     * 发布流程
//     *
//     * @param deployFlowRequest flowModuleId 使用createFlow返回的flowModuleId
//     * @return
//     */
//    @RequestMapping(value = "/deployFlow", method = {RequestMethod.POST})
//    public BaseResponse<DeployFlowResponse> deployFlow(@RequestBody DeployFlowRequest deployFlowRequest) {
//        try {
//            DeployFlowResult deployFlowResult = logicFlowService.deployFlow(deployFlowRequest);
//            DeployFlowResponse deployFlowResponse = new DeployFlowResponse();
//            BaseResponse<DeployFlowResponse> baseResponse = BaseResponse.make(deployFlowResponse);
//            BeanUtils.copyProperties(deployFlowResult, baseResponse);
//            BeanUtils.copyProperties(deployFlowResult, deployFlowResponse);
//            return baseResponse;
//        } catch (Exception e) {
//            LOGGER.error("deployFlow error", e);
//            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
//        }
//    }
//
//
//    /**
//     * 查询流程
//     *
//     * @param getFlowModuleRequest 单个流程请求参数
//     * @return 单个流程数据
//     */
//    @RequestMapping(value = "/queryFlow", method = {RequestMethod.POST})
//    public BaseResponse<GetFlowModuleResponse> queryFlow(@RequestBody GetFlowModuleRequest getFlowModuleRequest) {
//        try {
//            FlowModuleResult flowModuleResult = logicFlowService.getFlowModule(getFlowModuleRequest);
//            GetFlowModuleResponse getFlowModuleResponse = new GetFlowModuleResponse();
//            BaseResponse<GetFlowModuleResponse> baseResponse = BaseResponse.make(getFlowModuleResponse);
//            BeanUtils.copyProperties(flowModuleResult, baseResponse);
//            BeanUtils.copyProperties(flowModuleResult, getFlowModuleResponse);
//            return baseResponse;
//        } catch (Exception e) {
//            LOGGER.error("queryFlow error", e);
//            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
//        }
//    }
//
//
//    /**
//     * 分页查询流程列表
//     *
//     * @param getFlowModuleListRequest 分页请求参数
//     * @return 返回列表数据
//     */
//    @RequestMapping(value = "/queryFlowList", method = {RequestMethod.POST})
//    public BaseResponse<FlowModuleListResponse> queryFlowList(@RequestBody GetFlowModuleListRequest getFlowModuleListRequest) {
//        try {
//            FlowModuleListResponse flowModuleListResponse = logicFlowService.getFlowModuleList(getFlowModuleListRequest);
//            BaseResponse<FlowModuleListResponse> baseResponse = BaseResponse.make(flowModuleListResponse);
//            baseResponse.setCode(ErrorEnum.SUCCESS.getErrNo());
//            baseResponse.setMessage(ErrorEnum.SUCCESS.getErrMsg());
//            return baseResponse;
//        } catch (Exception e) {
//            LOGGER.error(" queryFlowList error", e);
//            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
//        }
//    }
//
////    @CrossOrigin(origins = "*")
////    @RequestMapping(path = "/m/{path}", method = RequestMethod.GET)
////    @ResponseBody
////    public BaseResponse doModularityGet(HttpServletRequest request, HttpServletResponse response,
////                                        @PathVariable String path) {
////        return null;
////    }
//
//    @CrossOrigin(origins = "*")
//    @RequestMapping(path = "/m/**", method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponse doModularityPost(HttpServletRequest request) throws IOException {
//        String path = getFlowPath(request.getRequestURI());
//        FlowModuleResult flowModule = getFlowModuleByPath(path, false);
//        if (flowModule == null) {
//            return new BaseResponse<>(ErrorEnum.FLOW_NOT_EXIST);
//        }
//        StartProcessParam startProcessParam = new StartProcessParam();
//        startProcessParam.setFlowModuleId(flowModule.getFlowModuleId());
//        String requestBodyString = getRequestBody(request);
//        startProcessParam.setVariables(JSON.parseObject(requestBodyString));
//        StartProcessResult startProcessResult = turboProcessEngine.startProcess(startProcessParam);
//        if (startProcessResult.getCode() != ErrorEnum.SUCCESS.getErrNo()) {
//            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
//        }
//        JSONObject properties = startProcessResult.getVariables();
//        return buildRestResp(properties);
//    }
//
//    @CrossOrigin(origins = "*")
//    @RequestMapping(path = "/debug/m/**", method = RequestMethod.POST)
//    @ResponseBody
//    public DebugResult debugModularity(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String path = getFlowPath(request.getRequestURI());
//        FlowModuleResult flowModule = getFlowModuleByPath(path, true);
//        StartProcessParam startProcessParam = new StartProcessParam();
//        startProcessParam.setFlowModuleId(flowModule.getFlowModuleId());
//        String requestBodyString = getRequestBody(request);
//        startProcessParam.setVariables(JSON.parseObject(requestBodyString));
//        DebugResult debugResult = turboProcessEngine.debugProcess(startProcessParam);
//        debugResult = buildDebugRestResp(debugResult);
//        return debugResult;
//    }
//
//    @RequestMapping(value = "/test", method = {RequestMethod.GET})
//    public String test() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", 1);
//        List<Map<String, Object>> db1 = rdBSqlExecutor.selectList("db1", "select * from ei_flow_instance where id=#{id}", map);
//        return "1";
//    }
//
//
//    private FlowModuleResult getFlowModuleByPath(String path, boolean isDebug) {
//        GetFlowModuleParam param = new GetFlowModuleParam();
//        param.setFlowType(FlowTypeEnum.http.name());
//        param.setFlowKey(path);
//        param.setDebug(isDebug);
//        FlowModuleResult flowModule = turboProcessEngine.getFlowModule(param);
//        return flowModule;
//    }
//
//    private String getRequestBody(HttpServletRequest request) throws IOException {
//        StringBuilder requestBody = new StringBuilder();
//        String line;
//        BufferedReader reader = request.getReader();
//        while ((line = reader.readLine()) != null) {
//            requestBody.append(line);
//        }
//        return requestBody.toString();
//    }
//
//    private String getFlowPath(String requestURI) {
//        int flag = requestURI.indexOf("/m/");
//        if (flag > -1) {
//            return requestURI.substring(flag + 2);
//        }
//        return requestURI;
//    }
//
//    private BaseResponse buildRestResp(JSONObject dataMap){
//        Boolean hasError = dataMap.getBoolean(Constants.SYSTEM_CONTEXT_PROPERTIES.HAS_ERROR);
//        if (hasError != null && hasError) {
//            Integer code = dataMap.getInteger(Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_CODE);
//            String message = dataMap.getString(Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_MSG);
//            BaseResponse baseResponse = new BaseResponse(code, message);
//            return baseResponse;
//        }
//        Map<String, Object> data = buildRespData(dataMap);
//        return BaseResponse.make(data);
//    }
//
//    private DebugResult buildDebugRestResp(DebugResult debugResult){
//        JSONObject dataMap = debugResult.getVariables();
//        if (debugResult.getCode() != ErrorEnum.SUCCESS.getErrNo()) {
//            return debugResult;
//        }
//
//        Boolean hasError = dataMap.getBoolean(Constants.SYSTEM_CONTEXT_PROPERTIES.HAS_ERROR);
//        if (hasError != null && hasError) {
//            Integer code = dataMap.getInteger(Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_CODE);
//            String message = dataMap.getString(Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_MSG);
//            debugResult.setCode(code);
//            debugResult.setMessage(message);
//        }
//        Map<String, Object> respData = buildRespData(dataMap);
//        dataMap.put("data", respData);
//        return debugResult;
//    }
//
//    private Map<String, Object> buildRespData(JSONObject dataMap){
//        Map<String, Object> ret = new HashMap<>();
//        JSONArray jsonArray = dataMap.getJSONArray(Constants.SYSTEM_CONTEXT_PROPERTIES.HTTP_RESP_KEYS);
//        if (jsonArray != null) {
//            List<String> respKey = jsonArray.toJavaList(String.class);
//            if (CollectionUtils.isNotEmpty(respKey)) {
//                for (String key : respKey) {
//                    Object val = dataMap.get(key);
//                    ret.put(key, val);
//                }
//            }
//        }
//        return ret;
//    }
//}
