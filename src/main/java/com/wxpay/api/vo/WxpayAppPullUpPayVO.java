package com.wxpay.api.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.response.WxpayUnifiedOrderResponse;
import com.wxpay.api.util.WxpaySignature;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@ToString
@NoArgsConstructor
public class WxpayAppPullUpPayVO implements Serializable {
    private String appid;
    private String partnerid;
    private String prepayid;
    @JSONField(name = "package")
    private String packageAlia;
    private String noncestr;
    private String timestamp;
    private String sign;

    public WxpayAppPullUpPayVO(WxpayUnifiedOrderResponse response, String appid, String merid, String key) throws Exception {
        this.appid = appid;
        this.partnerid = merid;
        this.prepayid = response.getPrepayId();
        this.packageAlia = "Sign=WXPay";
        this.noncestr = response.getNonceStr();
        this.timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        this.sign = WxpaySignature.getSign(this, key, WxpaySignature.SIGN_TYPE_MD5);
    }
}
