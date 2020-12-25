package com.wxpay.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.WxpayResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public class WxpayCreateRentBillResponse extends WxpayResponse {
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

    /**
     * 小程序跳转appid
     */
    @JSONField(name = "miniprogram_appid")
    private String miniprogramAppid;

    /**
     * 小程序跳转路径
     */
    @JSONField(name = "miniprogram_path")
    private String miniprogramPath;

    /**
     * 小程序跳转username
     */
    @JSONField(name = "miniprogram_username")
    private String miniprogramUsername;

    /**
     * 跳转微信侧小程序订单数据
     */
    @JSONField(name = "package")
    private String packageStr;
}
