package com.wxpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.WxpayRequest;
import com.wxpay.api.constant.WxpayApiAddress;
import com.wxpay.api.response.WxpayUnifiedOrderResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public class WxpayUnifiedOrderRequest extends WxpayRequest<WxpayUnifiedOrderResponse> {
    @JSONField(name = "device_info")
    private String deviceInfo;

    @JSONField(name = "nonce_str")
    private String nonceStr;

    private String sign;

    @JSONField(name = "sign_type")
    private String signType;

    private String body;

    private String detail;

    private String attach;

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    @JSONField(name = "fee_type")
    private String feeType;

    @JSONField(name = "total_fee")
    private Integer totalFee;

    @JSONField(name = "spbill_create_ip")
    private String spbillCreateIp;

    @JSONField(name = "time_start")
    private String timeStart;

    @JSONField(name = "time_expire")
    private String timeExpire;

    @JSONField(name = "goods_tag")
    private String goodsTag;

    @JSONField(name = "notify_url")
    private String notifyUrl;

    @JSONField(name = "trade_type")
    private String tradeType;

    @JSONField(name = "product_id")
    private String productId;

    @JSONField(name = "limit_pay")
    private String limitPay;

    private String openid;

    private String receipt;

    @JSONField(name = "scene_info")
    private String sceneInfo;

    /**
     * 获取请求地址
     *
     * @return 请求地址
     */
    @Override
    public String gainApiRequestAddr() {
        return WxpayApiAddress.UNIFIED_ORDER;
    }

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    @Override
    public Class<WxpayUnifiedOrderResponse> gainResponseClass() {
        return WxpayUnifiedOrderResponse.class;
    }

    @Override
    public String gainLogPre() {
        return "微信支付创建";
    }
}
