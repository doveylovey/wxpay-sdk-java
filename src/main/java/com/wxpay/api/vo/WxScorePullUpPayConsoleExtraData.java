package com.wxpay.api.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.response.WxpayCreateRentBillResponse;
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
public class WxScorePullUpPayConsoleExtraData implements Serializable {
    @JSONField(name = "mch_id")
    private String mchId;

    private String timestamp;

    @JSONField(name = "nonce_str")
    private String nonceStr;

    @JSONField(name = "sign_type")
    private String signType;

    private String sign;

    @JSONField(name = "package")
    private String packageAlia;

    public WxScorePullUpPayConsoleExtraData(WxpayCreateRentBillResponse response, String key) throws Exception {
        this.mchId = response.getMchId();
        this.packageAlia = response.getPackageStr();
        this.nonceStr = response.getNonceStr();
        this.timestamp = (System.currentTimeMillis() / 1000 + "");
        this.signType = WxpaySignature.SIGN_TYPE_HMAC_SHA256;
        this.sign = WxpaySignature.getSign(this, key, WxpaySignature.SIGN_TYPE_HMAC_SHA256);
    }
}
