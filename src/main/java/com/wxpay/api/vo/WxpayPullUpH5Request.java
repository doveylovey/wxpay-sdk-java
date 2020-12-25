package com.wxpay.api.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
public class WxpayPullUpH5Request {
    private String timeStamp;
    private String nonceStr;
    @JSONField(name = "package")
    private String packageAlia;
    private String signType;
    private String appId;
    private String paySign;

//    public WxpayPullUpH5Request(WxpayUnifiedOrderResponse response,String key) throws IllegalAccessException {
//        this.timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
//        this.nonceStr = response.getNonceStr();
//        this.packageAlia = "prepay_id="+ response.getPrepayId();
//        this.signType = "MD5";
//        WxpaySignature.object2UrlParams(this,key,null);
//    }
//
//        map2.put("package", "prepay_id="+ prepay_id);
//        map2.put("signType","MD5");
//        map2.put("appId", WxPayConfig.APP_ID);
//        map2.put("paySign", Signature.getSign(map2, WxPayConfig.KEY));
//        map2.remove("appId");
//
//    PullUpPayParam
}
