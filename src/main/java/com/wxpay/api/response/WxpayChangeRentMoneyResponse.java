package com.wxpay.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.WxpayResponse;
import lombok.Data;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
public class WxpayChangeRentMoneyResponse extends WxpayResponse {
    /**
     * 商户服务订单号
     */
    @JSONField(name = "out_order_no")
    private String outOrderNo;

    /**
     * 服务ID
     */
    @JSONField(name = "service_id")
    private String serviceId;

    /**
     * 微信支付服务订单号
     */
    @JSONField(name = "order_id")
    private String orderId;
}
