package com.wxpay.api.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public class ObjectUtil {
    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null) {
            return false;
        } else if (pObj == "") {
            return false;
        } else {
            if (pObj instanceof String) {
                if (((String) pObj).length() == 0) {
                    return false;
                }
            } else if (pObj instanceof Collection) {
                if (((Collection) pObj).size() == 0) {
                    return false;
                }
            } else if (pObj instanceof Map && ((Map) pObj).size() == 0) {
                return false;
            }
            return true;
        }
    }

    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        } else if (pObj == "") {
            return true;
        } else {
            if (pObj instanceof String) {
                if (((String) pObj).length() == 0) {
                    return true;
                }
            } else if (pObj instanceof Collection) {
                if (((Collection) pObj).size() == 0) {
                    return true;
                }
            } else if (pObj instanceof Map && ((Map) pObj).size() == 0) {
                return true;
            }
            return false;
        }
    }
}
