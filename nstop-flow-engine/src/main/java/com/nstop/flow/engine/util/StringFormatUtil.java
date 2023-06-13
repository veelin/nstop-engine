package com.nstop.flow.engine.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linziwei
 * @date 2023/6/9
 */
public class StringFormatUtil {

    private static final Pattern pattern = Pattern.compile("\\#\\{(.*?)\\}");

    private static Matcher matcher;

    private StringFormatUtil() {
    }

    /**
     * 格式化字符串 字符串中使用{key}表示占位符
     *
     * @param sourStr 需要匹配的字符串
     * @param param   参数集
     * @return
     */

    public static String format(String sourStr, Map param) {
        String tagerStr = sourStr;
        if (param == null)
            return tagerStr;
        try {
            matcher = pattern.matcher(tagerStr);
            while (matcher.find()) {
                String key = matcher.group();
                String keyclone = key.substring(2, key.length() - 1).trim();
                Object value = param.get(keyclone);
                if (value != null)
                    tagerStr = tagerStr.replace(key, value.toString());
            }
        } catch (Exception e) {
            return null;
        }
        return tagerStr;
    }


    /**
     * 将下划线风格替换为驼峰风格
     *
     * @param inputString
     * @return
     */
    public static String underlineToCamelHump(String inputString) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '_') {
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }

    public static Map<String, Object> underlineToCamelHump(Map<String, Object> input) {
        Map<String, Object> retMap = new HashMap<>();
        input.entrySet().forEach(e->{
            retMap.put(underlineToCamelHump(e.getKey()), e.getValue());
        });
        return retMap;
    }


//    public static void main(String[] args) {
//
//
//        String url = "https://xxx.com/cfes?c=#{campaign_name}&af_siteid={af_siteid}&clickid={clickid}&android_id={android_id}&advertising_id={advertising_id}&idfa={idfa}";
//
//        Map map = new LinkedHashMap<>();
//
//        map.put("campaign_name", "111");
//
//        map.put("af_siteid", "222");
//
//        map.put("clickid", "333");
//
//        map.put("android_id", "444");
//
//        map.put("advertising_id", "555");
//
//        map.put("idfa", "");
//
//        System.out.println(format(url, map));
//
//    }

}