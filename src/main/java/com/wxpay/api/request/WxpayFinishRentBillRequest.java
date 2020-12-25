package com.wxpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.AbstractWxpayRentBillRequest;
import com.wxpay.api.constant.WxpayApiAddress;
import com.wxpay.api.response.WxpayFinishRentBillResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public class WxpayFinishRentBillRequest extends AbstractWxpayRentBillRequest<WxpayFinishRentBillResponse> {
    /**
     * 商户服务订单号
     */
    @JSONField(name = "out_order_no")
    private String outOrderNo;

    /**
     * 是否归还
     */
    private String returned;

    /**
     * 实际归还时间
     */
    @JSONField(name = "real_end_time")
    private String realEndTime;

    /**
     * 归还地点
     */
    @JSONField(name = "service_end_location")
    private String serviceEndLocation;

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
     * 完结凭证
     */
    @JSONField(name = "finish_ticket")
    private String finishTicket;

    /**
     * 分账标识
     */
    @JSONField(name = "profit_sharing")
    private String profitSharing;

    /**
     * 获取请求地址
     *
     * @return 请求地址
     */
    @Override
    public String gainApiRequestAddr() {
        return WxpayApiAddress.FINISH_RENT_BILL;
    }

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    @Override
    public Class<WxpayFinishRentBillResponse> gainResponseClass() {
        return WxpayFinishRentBillResponse.class;
    }

    @Override
    public String gainLogPre() {
        return "微信支付分完结";
    }
}
