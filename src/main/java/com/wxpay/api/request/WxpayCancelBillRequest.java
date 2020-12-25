package com.wxpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.AbstractWxpayRentBillRequest;
import com.wxpay.api.constant.WxpayApiAddress;
import com.wxpay.api.response.WxpayCancelBillResponse;
import lombok.*;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxpayCancelBillRequest extends AbstractWxpayRentBillRequest<WxpayCancelBillResponse> {
    /**
     * 商户服务订单号
     */
    @JSONField(name = "out_order_no")
    private String outOrderNo;

    /**
     * 撤销原因
     */
    private String reason;

    /**
     * 获取请求地址
     *
     * @return 请求地址
     */
    @Override
    public String gainApiRequestAddr() {
        return WxpayApiAddress.CANCEL_BILL;
    }

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    @Override
    public Class<WxpayCancelBillResponse> gainResponseClass() {
        return WxpayCancelBillResponse.class;
    }

    @Override
    public String gainLogPre() {
        return "微信支付分撤销";
    }
}
