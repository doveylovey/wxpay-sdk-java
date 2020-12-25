package com.wxpay.api.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Slf4j
public class MD5 {
    /**
     * MD5编码
     *
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = ByteUtil.byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
