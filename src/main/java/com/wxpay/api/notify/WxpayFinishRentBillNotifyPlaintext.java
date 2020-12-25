package com.wxpay.api.notify;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.constant.WxScoreState;
import lombok.Data;
import lombok.ToString;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@ToString
public class WxpayFinishRentBillNotifyPlaintext {
    /**
     * 单据状态
     */
    @JSONField(name = "state")
    private String state;

    /**
     * 服务ID
     */
    @JSONField(name = "service_id")
    private String serviceId;

    /**
     * 商户服务订单号
     */
    @JSONField(name = "out_order_no")
    private String outOrderNo;

    /**
     * 微信支付服务订单号
     */
    @JSONField(name = "order_id")
    private String orderId;

    /**
     * 商品名称
     */
    @JSONField(name = "goods_name")
    private String goodsName;

    /**
     * 是否归还
     */
    @JSONField(name = "returned")
    private String returned;

    /**
     * 租用时间
     */
    @JSONField(name = "start_time")
    private String startTime;

    /**
     * 押金金额
     */
    @JSONField(name = "deposit_amount")
    private Integer depositAmount;

    /**
     * 总金额
     */
    @JSONField(name = "total_amount")
    private Integer totalAmount;

    /**
     * 预计归还时间
     */
    @JSONField(name = "end_time")
    private String endTime;

    /**
     * 结单交易单号
     */
    @JSONField(name = "finish_transaction_id")
    private String finishTransactionId;

    public boolean isSuccess() {
        return (WxScoreState.USER_PAID.equals(state));
    }
}
