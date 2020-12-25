package com.wxpay.api.constant;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public interface WxpayApiAddress {
    /**
     * 统一下单
     */
    String UNIFIED_ORDER = "/pay/unifiedorder";

    /**
     * 小程序查询订单
     */
    String ORDER_QUERY = "/pay/orderquery";

    /**
     * 退款
     */
    String REFUND = "/secapi/pay/refund";

    /**
     * 退款查询
     */
    String REFUND_QUERY = "/pay/refundquery";

    /**
     * 微信支付分，免押租借
     * 创建租借订单接口
     */
    String CREATE_RENT_BILL = "/wxv/createrentbill";

    /**
     * 微信支付分，免押租借
     * 查询租借订单
     */
    String QUERY_RENT_BILL = "/wxv/queryrentbill";

    /**
     * 微信支付分，免押租借
     * 撤销租借订单
     */
    String CANCEL_BILL = "/wxv/cancelbill";

    /**
     * 微信支付分，免押租借
     * 完结租借订单
     */
    String FINISH_RENT_BILL = "/wxv/finishrentbill";

    /**
     * 微信支付分，免押租借
     * 修改租借订单
     */
    String CHANGE_RENT_MONEY = "/wxv/changerentmoney";
}
