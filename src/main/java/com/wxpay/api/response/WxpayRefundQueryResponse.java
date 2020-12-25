package com.wxpay.api.response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.WxpayResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public class WxpayRefundQueryResponse extends WxpayResponse {
    /**
     * 订单总退款次数
     */
    @JSONField(name = "total_refund_count")
    private String totalRefundCount;

    @JSONField(name = "transaction_id")
    private String transactionId;

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 订单金额
     * 订单总金额，单位为分，只能为整数
     */
    @JSONField(name = "total_fee")
    private Integer totalFee;

    @JSONField(name = "settlement_total_fee ")
    private Integer settlementTotalFee;

    @JSONField(name = "fee_type")
    private String feeType;

    /**
     * 现金支付金额
     */
    @JSONField(name = "cash_fee")
    private Integer cashFee;

    /**
     * 当前返回退款笔数
     */
    @JSONField(name = "refund_count")
    private Integer refundCount;

//    @JSONField(name="coupon_type_$n_$m")
//    private String coupon_type_$n_$m;
//
//    @JSONField(name="coupon_refund_fee_$n")
//    private String coupon_refund_fee_$n;
//
//    @JSONField(name="coupon_refund_count_$n")
//    private String coupon_refund_count_$n;
//
//    @JSONField(name="coupon_refund_id_$n_$m")
//    private String coupon_refund_id_$n_$m;
//
//    @JSONField(name="coupon_refund_fee_$n_$m")
//    private String coupon_refund_fee_$n_$m;

    List<WxpayRefundHistoryResponse> historys;

    public static <T extends WxpayResponse> T parseHistoryFromXml(JSONObject obj, Class<T> clazz) {
        JSONArray array = new JSONArray();
        for (int index = 0; ; index++) {
            if (obj.containsKey("out_refund_no_" + index)) {
                JSONObject element = new JSONObject();
                element.put("out_refund_no", obj.getString("out_refund_no_" + index));
                element.put("refund_account", obj.getString("refund_account_" + index));
                element.put("refund_channel", obj.getString("refund_channel_" + index));
                element.put("refund_fee", obj.getString("refund_fee_" + index));
                element.put("settlement_refund_fee", obj.getString("settlement_refund_fee_" + index));
                element.put("refund_id", obj.getString("refund_id_" + index));
                element.put("refund_recv_accout", obj.getString("refund_recv_accout_" + index));
                element.put("refund_status", obj.getString("refund_status_" + index));
                element.put("refund_success_time", obj.getString("refund_success_time_" + index));
                array.add(element);
            } else {
                break;
            }
        }
        obj.put("historys", array);
        return obj.toJavaObject(clazz);
    }

    public Integer getRefundFee() {
        int refundFee = 0;
        if (historys != null && historys.size() > 0) {
            for (WxpayRefundHistoryResponse hi : historys) {
                refundFee += hi.getRefundFee();
            }
        }
        return refundFee;
    }
}
