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
public class WxpayQueryRentBillResponse extends WxpayResponse {
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
     * 单据状态
     */
    private String state;

    /**
     * 商品名称
     */
    @JSONField(name = "goods_name")
    private String goodsName;

    /**
     * 是否归还
     */
    private String returned;

    /**
     * 租用时间
     */
    @JSONField(name = "start_time")
    private String startTime;

    /**
     * 租用地点
     */
    @JSONField(name = "service_location")
    private String serviceLocation;

    /**
     * 押金总额
     */
    @JSONField(name = "deposit_amount")
    private Integer depositAmount;

    /**
     * 租金规则 计费单位
     */
    @JSONField(name = "rent_unit")
    private String rentUnit;

    /**
     * 租金规则 计费单价费用
     */
    @JSONField(name = "rent_unit_fee")
    private Integer rentUnitFee;

    /**
     * 租金规则 计费说明
     */
    @JSONField(name = "rent_fee_desc")
    private String rentFeeDesc;

    /**
     * 实际归还时间
     */
    @JSONField(name = "real_end_time")
    private String realEndTime;

    /**
     * 租金费用
     */
    @JSONField(name = "rent_fee")
    private Integer rentFee;

    /**
     * 赔偿金费用
     */
    @JSONField(name = "compensation_fee")
    private Integer compensationFee;

    /**
     * 赔偿金说明
     */
    @JSONField(name = "compensation_fee_desc")
    private String compensationFeeDesc;

    /**
     * 归还地点
     */
    @JSONField(name = "service_end_location")
    private String serviceEndLocation;

    /**
     * 微信支付服务订单号
     */
    @JSONField(name = "order_id")
    private String orderId;

    /**
     * 完结凭证
     */
    @JSONField(name = "finish_ticket")
    private String finishTicket;

    /**
     * 回调数据包
     */
    private String attach;

    /**
     * 结单交易单号
     */
    @JSONField(name = "finish_transaction_id")
    private String finishTransactionId;
}
