package com.wxpay.api.constant;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public interface WxScoreState {
    /**
     * 商户下单已受理
     */
    String CREATED = "CREATED";

    /**
     * 用户接受
     */
    String USER_ACCEPTED = "USER_ACCEPTED";

    /**
     * 商户完结，用户待支付
     */
    String FINISHED = "FINISHED";

    /**
     * 用户已支付
     */
    String USER_PAID = "USER_PAID";

    /**
     * 商户撤销订单
     */
    String REVOKED = "REVOKED";

    /**
     * 订单已失效. “商户下单已受理”状态超过1小时未变动，则订单失效
     */
    String EXPIRED = "EXPIRED";
}
