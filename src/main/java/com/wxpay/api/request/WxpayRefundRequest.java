package com.wxpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.WxpayRequest;
import com.wxpay.api.constant.WxpayApiAddress;
import com.wxpay.api.response.WxpayRefundResponse;
import lombok.Data;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
public class WxpayRefundRequest extends WxpayRequest<WxpayRefundResponse> {
    /**
     * 微信订单号（与商户订单号二选一）
     */
    @JSONField(name = "transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    @JSONField(name = "out_refund_no")
    private String outRefundNo;

    /**
     * 订单金额,单位分
     */
    @JSONField(name = "total_fee")
    private Integer totalFee;

    /**
     * 退款金额，单位分
     */
    @JSONField(name = "refund_fee")
    private Integer refundFee;

    /**
     * 货币种类(非必填)
     */
    @JSONField(name = "refund_fee_type")
    private String refundFeeType;

    /**
     * 退款原因(非必填)
     */
    @JSONField(name = "refund_desc")
    private String refund_desc;

    /**
     * 退款资金来源
     */
    @JSONField(name = "refund_account")
    private String refundAccount;

    /**
     * 退款结果通知url
     */
    @JSONField(name = "notify_url")
    private String notifyUrl;

    /**
     * 获取请求地址
     *
     * @return 请求地址
     */
    @Override
    public String gainApiRequestAddr() {
        return WxpayApiAddress.REFUND;
    }

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */

    @Override
    public Class<WxpayRefundResponse> gainResponseClass() {
        return WxpayRefundResponse.class;
    }

    @Override
    public String gainLogPre() {
        return "微信支付分退款";
    }
}
