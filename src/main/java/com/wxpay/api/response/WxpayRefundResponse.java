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
public class WxpayRefundResponse extends WxpayResponse {
    @JSONField(name = "transaction_id")
    private String transactionId;

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    @JSONField(name = "out_refund_no")
    private String outRefundNo;

    @JSONField(name = "refund_id")
    private String refundId;

    @JSONField(name = "refund_fee")
    private String refundFee;

    @JSONField(name = "settlement_refund_fee")
    private String settlementRefundFee;

    @JSONField(name = "total_fee")
    private String totalFee;

    @JSONField(name = "settlement_total_fee ")
    private String settlementTotalFee;

    @JSONField(name = "fee_type")
    private String feeType;

    @JSONField(name = "cash_fee")
    private String cashFee;

    @JSONField(name = "cash_fee_type ")
    private String cashFeeType;

    @JSONField(name = "cash_refund_fee")
    private String cashRefundFee;

    @JSONField(name = "coupon_refund_fee")
    private String coupon_refund_fee;

    @JSONField(name = "coupon_refund_count")
    private String coupon_refund_count;

//    List<WxpayRefundHistoryResponse> historys;
}
