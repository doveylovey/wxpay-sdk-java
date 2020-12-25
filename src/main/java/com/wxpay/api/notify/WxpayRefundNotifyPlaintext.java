package com.wxpay.api.notify;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.constant.WxpayApiCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
public class WxpayRefundNotifyPlaintext implements Serializable {
    @JSONField(name = "transaction_id")
    private String transactionId;

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    @JSONField(name = "refund_id")
    private String refundId;

    @JSONField(name = "out_refund_no")
    private String outRefundNo;

    @JSONField(name = "total_fee")
    private Integer totalFee;

    @JSONField(name = "settlement_total_fee")
    private Integer settlementTotalFee;

    @JSONField(name = "refund_fee")
    private Integer refundFee;

    @JSONField(name = "settlement_refund_fee")
    private Integer settlementRefundFee;

    @JSONField(name = "refund_status")
    private String refundStatus;

    @JSONField(name = "success_time")
    private String successTime;

    @JSONField(name = "refund_recv_accout")
    private String refundRecvAccout;

    @JSONField(name = "refund_account")
    private String refundAccount;

    @JSONField(name = "refund_request_source")
    private String refundRequestSource;

    public boolean isSuccess() {
        return WxpayApiCode.REFUND_SUCCESS.equals(refundStatus);
    }
}


