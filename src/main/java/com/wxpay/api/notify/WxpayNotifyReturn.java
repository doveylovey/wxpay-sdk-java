package com.wxpay.api.notify;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxpayNotifyReturn {
    @JSONField(name = "return_code")
    private String returnCode;

    @JSONField(name = "return_msg")
    private String returnMsg;

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    public String successText() {
        return new WxpayNotifyReturn("SUCCESS", "OK").toJSONString();
    }

    public String failText() {
        return new WxpayNotifyReturn("FAIL", "").toJSONString();
    }

    public String failText(String returnMsg) {
        return new WxpayNotifyReturn("FAIL", returnMsg).toJSONString();
    }
}
