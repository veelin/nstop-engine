package com.nstop.flow.engine.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;

public class InstanceDataUtil {

    private InstanceDataUtil() {
    }
    public static JSONObject getInstanceDataMap(String dataStr){
        return JSONObject.parseObject(dataStr);
    }
    public static String getInstanceDataListStr(JSONObject data){
        if (data == null) {
            return "";
        }
        return JSONObject.toJSONString(data);
    }
    public static String getStringValue(JSONObject instanceDataMap, String key) {
        if (MapUtils.isEmpty(instanceDataMap)) {
            return null;
        }
        Object instanceData = instanceDataMap.get(key);
        if (instanceData == null) {
            return null;
        }
        return String.valueOf(instanceData);
    }


    public static void putValue(JSONObject instanceDataMap, String key, Object val) {
        if (instanceDataMap == null) {
            return ;
        }
        instanceDataMap.put(key, val);
    }

}
