//package com.nstop.flow.engine.tool.http;
//
//import com.alibaba.fastjson.JSONObject;
//import com.nstop.flow.engine.common.RuntimeContext;
//
///**
// * @author linziwei
// * @date 2023/1/29
// */
//public class HttpRuntimeContent extends RuntimeContext {
//
//
//    public JSONObject getHeader() {
//        return getRuntimeData().getJSONObject(HttpContentConstant.header);
//    }
//
//    public void setHeader(JSONObject header) {
//        getRuntimeData().put(HttpContentConstant.header, header);
//    }
//
//    public JSONObject getRequest() {
//        return getRuntimeData().getJSONObject(HttpContentConstant.request);
//    }
//
//    public void setRequest(JSONObject request) {
//        getRuntimeData().put(HttpContentConstant.request, request);
//    }
//
//    public JSONObject getResponse() {
//        return getRuntimeData().getJSONObject(HttpContentConstant.response);
//    }
//
//    public void setResponse(JSONObject response) {
//        getRuntimeData().put(HttpContentConstant.response, response);
//    }
//
//    public String getTraceId() {
//        return getRuntimeData().getString(HttpContentConstant.tranceId);
//    }
//
//    public void setTraceId(String traceId) {
//        getRuntimeData().put(HttpContentConstant.tranceId, traceId);
//    }
//}
