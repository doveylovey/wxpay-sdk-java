package com.wxpay.api.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.response.WxpayUnifiedOrderResponse;
import com.wxpay.api.util.WxpaySignature;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public class WxpayMinPullUpPayVO implements Serializable {
    private String timeStamp;
    private String nonceStr;
    @JSONField(name = "package")
    private String packageAlia;
    private String signType;
    private String paySign;

    public WxpayMinPullUpPayVO(WxpayUnifiedOrderResponse resp, String appid, String key) throws Exception {
        this.timeStamp = System.currentTimeMillis() / 1000 + "";
        this.nonceStr = resp.getNonceStr();
        this.packageAlia = "prepay_id=" + resp.getPrepayId();
        this.signType = WxpaySignature.SIGN_TYPE_MD5;
        Map map = new HashMap();
        map.put("timeStamp", timeStamp);
        map.put("nonceStr", nonceStr);
        map.put("package", packageAlia);
        map.put("signType", signType);
        map.put("appId", appid);
        this.paySign = WxpaySignature.getSign(map, key, signType);
    }
}
