package com.wxpay.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.WxpayResponse;
import com.wxpay.api.constant.WxpayApiCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public class WxpayUnifiedOrderResponse extends WxpayResponse {
    /**
     * 交易类型
     */
    @JSONField(name = "trade_type")
    private String tradeType;

    /**
     * 预支付交易会话标识
     */
    @JSONField(name = "prepay_id")
    private String prepayId;

    /**
     * 二维码链接
     */
    @JSONField(name = "code_url")
    private String codeUrl;

    /**
     * 支付跳转链接
     */
    @JSONField(name = "mweb_url")
    private String mwebUrl;

    /**
     * 交易是否成功
     *
     * @return
     */
    @Override
    public boolean isSuccess() {
        return WxpayApiCode.RESULT_CODE_SUCCESS.equals(this.getResultCode());
    }
}
