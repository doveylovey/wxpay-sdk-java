package com.wxpay.api.notify;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.WxpayResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public class WxpayUnifiedOrderNotify extends WxpayResponse {
    @JSONField(name = "appid")
    private String appid;

    @JSONField(name = "mch_id")
    private String mchId;

    @JSONField(name = "device_info")
    private String deviceInfo;

    @JSONField(name = "nonce_str")
    private String nonceStr;

    @JSONField(name = "sign")
    private String sign;

    @JSONField(name = "sign_type")
    private String signType;

    @JSONField(name = "openid")
    private String openid;

    @JSONField(name = "is_subscribe")
    private String isSubscribe;

    @JSONField(name = "trade_type")
    private String tradeType;

    @JSONField(name = "bank_type")
    private String bankType;

    @JSONField(name = "total_fee")
    private String totalFee;

    @JSONField(name = "settlement_total_fee ")
    private String settlementTotalFee;

    @JSONField(name = "fee_type")
    private String feeType;

    @JSONField(name = "cash_fee")
    private String cashFee;

    @JSONField(name = "cash_fee_type")
    private String cashFeeType;

    @JSONField(name = "coupon_fee")
    private String couponFee;

    @JSONField(name = "coupon_count")
    private String couponCount;

    @JSONField(name = "transaction_id")
    private String transactionId;

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    @JSONField(name = "attach")
    private String attach;

    @JSONField(name = "time_end")
    private String timeEnd;
}
