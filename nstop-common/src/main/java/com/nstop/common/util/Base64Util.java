package com.nstop.common.util;

import java.util.Base64;

/**
 * @author: origindoris
 * @Title: Base64Util
 * @Description:
 * @date: 2022/10/18 15:05
 */
public class Base64Util {
    /**
     * 加密
     *
     * @param data
     * @return
     */
    public static String encoder(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    /**
     * 解密
     *
     * @param data
     * @return
     */
    public static String decoder(String data) {
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        return new String(decodedBytes);
    }


    /**
     * url加密
     * @param originUrl
     * @return
     */
    public static String urlEncoder(String originUrl){
        return Base64.getUrlEncoder().encodeToString(originUrl.getBytes());
    }


    /**
     * url解密
     * @param encoderUrl
     * @return
     */
    public static String urlDecoder(String encoderUrl){
        byte[] decode = Base64.getUrlDecoder().decode(encoderUrl);
        return new String(decode);
    }

}
