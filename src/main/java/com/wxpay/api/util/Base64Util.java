package com.wxpay.api.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public class Base64Util {
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}