package com.nstop.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nstop.flow.engine.common.*;
import com.nstop.flow.engine.engine.TurboProcessEngine;
import com.nstop.flow.engine.param.GetFlowModuleParam;
import com.nstop.flow.engine.param.StartProcessParam;
import com.nstop.flow.engine.result.*;
import com.nstop.flow.engine.util.InstanceDataUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 */
@RestController
@RequestMapping("/n_stop")
public class FlowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowController.class);

    @Resource
    private TurboProcessEngine turboProcessEngine;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/m/**", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doModularityPost(HttpServletRequest request) throws IOException {
        String path = getFlowPath(request.getRequestURI());
        FlowModuleResult flowModule = getFlowModuleByPath(path, false);
        if (flowModule == null) {
            return new BaseResponse<>(ErrorEnum.FLOW_NOT_EXIST);
        }
        String requestBodyString = getRequestBody(request);
        StartProcessParam startProcessParam = new StartProcessParam();
        startProcessParam.setFlowModuleId(flowModule.getFlowModuleId());
        JSONObject jsonObject = Optional.of(JSON.parseObject(requestBodyString)).orElse(new JSONObject());
        startProcessParam.setVariables(jsonObject);
        InstanceDataUtil.putValue(startProcessParam.getVariables(), Constants.SYSTEM_CONTEXT_PROPERTIES.ENGINE_TYPE_DATA_KEY, EngineTypeEnum.instant.name());
        StartProcessResult startProcessResult = turboProcessEngine.startProcess(startProcessParam);
        if (startProcessResult.getCode() != ErrorEnum.SUCCESS.getErrNo()) {
            return new BaseResponse<>(ErrorEnum.SYSTEM_ERROR);
        }
        JSONObject properties = startProcessResult.getVariables();
        return buildRestResp(properties);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/debug/m/**", method = RequestMethod.POST)
    @ResponseBody
    public DebugResult debugModularity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = getFlowPath(request.getRequestURI());
        String requestBodyString = getRequestBody(request);
        StartProcessParam startProcessParam = buildStartProcessParam(path, requestBodyString, true);
        DebugResult debugResult = turboProcessEngine.debugProcess(startProcessParam);
        return buildDebugRestResp(debugResult);
    }

    public StartProcessParam buildStartProcessParam(String path, String requestBody, boolean isDebug){
        FlowModuleResult flowModule = getFlowModuleByPath(path, isDebug);
        StartProcessParam startProcessParam = new StartProcessParam();
        startProcessParam.setFlowModuleId(flowModule.getFlowModuleId());
        JSONObject jsonObject = Optional.of(JSON.parseObject(requestBody)).orElse(new JSONObject());
        startProcessParam.setVariables(jsonObject);
        InstanceDataUtil.putValue(startProcessParam.getVariables(), Constants.SYSTEM_CONTEXT_PROPERTIES.ENGINE_TYPE_DATA_KEY, EngineTypeEnum.instant.name());
        return startProcessParam;
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

    private BaseResponse buildRestResp(JSONObject dataMap){
        Boolean hasError = dataMap.getBoolean(Constants.SYSTEM_CONTEXT_PROPERTIES.HAS_ERROR);
        if (hasError != null && hasError) {
            Integer code = dataMap.getInteger(Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_CODE);
            String message = dataMap.getString(Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_MSG);
            BaseResponse baseResponse = new BaseResponse(code, message);
            return baseResponse;
        }
        Map<String, Object> data = buildRespData(dataMap);
        return BaseResponse.make(data);
    }

    private DebugResult buildDebugRestResp(DebugResult debugResult){
        JSONObject dataMap = debugResult.getVariables();
        if (debugResult.getCode() != ErrorEnum.SUCCESS.getErrNo()) {
            return debugResult;
        }

        Boolean hasError = dataMap.getBoolean(Constants.SYSTEM_CONTEXT_PROPERTIES.HAS_ERROR);
        if (hasError != null && hasError) {
            Integer code = dataMap.getInteger(Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_CODE);
            String message = dataMap.getString(Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_MSG);
            debugResult.setCode(code);
            debugResult.setMessage(message);
        }
        Map<String, Object> respData = buildRespData(dataMap);
        dataMap.put("data", respData);
        return debugResult;
    }

    private Map<String, Object> buildRespData(JSONObject dataMap){
        Map<String, Object> ret = new HashMap<>();
        JSONArray jsonArray = dataMap.getJSONArray(Constants.SYSTEM_CONTEXT_PROPERTIES.HTTP_RESP_KEYS);
        if (jsonArray != null) {
            List<String> respKey = jsonArray.toJavaList(String.class);
            if (CollectionUtils.isNotEmpty(respKey)) {
                for (String key : respKey) {
                    Object val = dataMap.get(key);
                    ret.put(key, val);
                }
            }
        }
        return ret;
    }
}
