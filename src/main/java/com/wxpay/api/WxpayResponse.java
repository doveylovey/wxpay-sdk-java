package com.wxpay.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.constant.WxpayApiCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public abstract class WxpayResponse implements Serializable {
    @JSONField(name = "return_code")
    private String returnCode;

    @JSONField(name = "return_msg")
    private String returnMsg;

    @JSONField(name = "result_code")
    private String resultCode;

    @JSONField(name = "err_code")
    private String errCode;

    @JSONField(name = "err_code_des")
    private String errCodeDes;

    private String sign;

    private String appid;

    @JSONField(name = "mch_id")
    private String mchId;

    @JSONField(name = "device_info")
    private String deviceInfo;

    @JSONField(name = "nonce_str")
    private String nonceStr;

    /**
     * 交易是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return WxpayApiCode.RETURN_CODE_SUCCESS.equals(returnCode) && WxpayApiCode.RESULT_CODE_SUCCESS.equals(resultCode);
    }
}
