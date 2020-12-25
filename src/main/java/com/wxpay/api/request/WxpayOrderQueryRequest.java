package com.wxpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.constant.WxpayApiAddress;
import com.wxpay.api.WxpayRequest;
import com.wxpay.api.response.WxpayOrderQueryResponse;
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
public class WxpayOrderQueryRequest extends WxpayRequest<WxpayOrderQueryResponse> {
    @JSONField(name = "transaction_id")
    private String transactionId;

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 获取请求地址
     *
     * @return
     */
    @Override
    public String gainApiRequestAddr() {
        return WxpayApiAddress.ORDER_QUERY;
    }

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    @Override
    public Class<WxpayOrderQueryResponse> gainResponseClass() {
        return WxpayOrderQueryResponse.class;
    }

    @Override
    public String gainLogPre() {
        return "微信支付查询";
    }
}
