package com.wxpay.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public class WxpaySignature {
    private static final String SIGN_FIELD = "sign";
    private static final String SIGN_IGNORE_SIGN = "sign";
    //    private static final String SIGN_IGNORE_APIREQUESTADDR = "apiRequestAddr";
//    private static final String SIGN_IGNORE_RESPONSECLASS = "responseClass";
    public static final String SIGN_TYPE_MD5 = "MD5";
    public static final String SIGN_TYPE_HMAC_SHA256 = "HMAC-SHA256";

    private static String object2UrlParams(JSONObject jsonObj, String key, String... ignoreFields) throws IllegalAccessException {
        ArrayList<String> list = new ArrayList<String>();
        if (ignoreFields != null && ignoreFields.length != 0) {
            for (String ignoreField : ignoreFields) {
                jsonObj.remove(ignoreField);
            }
        }
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            if (ObjectUtil.isNotEmpty(entry.getValue())) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        sb.append("key=").append(key);
        return sb.toString();
    }

    private static String object2UrlParams(Object o, String key, String... ignoreFields) throws IllegalAccessException {
        JSONObject jsonObj = JSON.parseObject(JSON.toJSONString(o));
        return object2UrlParams(jsonObj, key, ignoreFields);
    }

    /**
     * 签名算法
     *
     * @param obj
     * @param key
     * @param signType
     * @return
     * @throws IllegalAccessException
     */
    public static String getSign(Object obj, String key, String signType) throws Exception {
        String result = object2UrlParams(obj, key, SIGN_IGNORE_SIGN);
        if (SIGN_TYPE_MD5.equals(signType)) {
            result = MD5.encode(result).toUpperCase();
        } else if (SIGN_TYPE_HMAC_SHA256.equals(signType)) {
            result = HMACSHA256.encode(result, key);
        }
        return result;
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     *
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(String responseString, String key, String signType) throws Exception {
        Map<String, Object> map = XMLParser.getJSONObjectFromXML(responseString);
        String signFromAPIResponse = map.get(SIGN_FIELD).toString();
        if (signFromAPIResponse == "" || signFromAPIResponse == null) {
            return false;
        }
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = WxpaySignature.getSign(map, key, signType);
        if (!signForAPIResponse.equals(signFromAPIResponse)) {
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
            return false;
        }
        return true;
    }
}
