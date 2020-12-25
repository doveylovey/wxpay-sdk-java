package com.wxpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.AbstractWxpayRentBillRequest;
import com.wxpay.api.constant.WxpayApiAddress;
import com.wxpay.api.response.WxpayChangeRentMoneyResponse;
import lombok.Data;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
public class WxpayChangeRentMoneyRequest extends AbstractWxpayRentBillRequest<WxpayChangeRentMoneyResponse> {
    /**
     * 商户服务订单号
     */
    @JSONField(name = "out_order_no")
    private String outOrderNo;

    /**
     * 修改原因
     */
    @JSONField(name = "reason")
    private String reason;

    /**
     * 总金额
     */
    @JSONField(name = "total_amount")
    private Integer totalAmount;

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
     * 赔偿金费用说明
     */
    @JSONField(name = "compensation_fee_desc")
    private String compensationFeeDesc;

    /**
     * 获取请求地址
     *
     * @return 请求地址
     */
    @Override
    public String gainApiRequestAddr() {
        return WxpayApiAddress.CHANGE_RENT_MONEY;
    }

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    @Override
    public Class<WxpayChangeRentMoneyResponse> gainResponseClass() {
        return WxpayChangeRentMoneyResponse.class;
    }

    @Override
    public String gainLogPre() {
        return "微信支付分修改金额";
    }
}
