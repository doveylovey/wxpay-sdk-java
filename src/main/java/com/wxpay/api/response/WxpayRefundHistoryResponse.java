package com.wxpay.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public class WxpayRefundHistoryResponse {
    @JSONField(name = "out_refund_no")
    private String outRefundNo;

    /**
     * 退款资金来源
     */
    @JSONField(name = "refund_account")
    private String refundAccount;

    /**
     * 退款渠道
     */
    @JSONField(name = "refund_channel")
    private String refundChannel;

    /**
     * 申请退款金额
     */
    @JSONField(name = "refund_fee")
    private Integer refundFee;

    /**
     * 退款金额 (可能没有)
     * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    @JSONField(name = "settlement_refund_fee")
    private Integer settlementRefundFee;

    /**
     * 微信退款单号
     */
    @JSONField(name = "refund_id")
    private String refundId;

    /**
     * 退款入账账户
     * 取当前退款单的退款入账方
     * 1）退回银行卡： {银行名称}{卡类型}{卡尾号}
     * 2）退回支付用户零钱: 支付用户零钱
     * 3）退还商户: 商户基本账户 商户结算银行账户
     * 4）退回支付用户零钱通:  支付用户零钱通
     */
    @JSONField(name = "refund_recv_accout")
    private String refundRecvAccout;

    /**
     * 退款状态
     * SUCCESS—退款成功
     * REFUNDCLOSE—退款关闭。
     * PROCESSING—退款处理中
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
     */
    @JSONField(name = "refund_status")
    private String refundStatus;

    /**
     * 退款成功时间
     */
    @JSONField(name = "refund_success_time")
    private String refundSuccessTime;
}
