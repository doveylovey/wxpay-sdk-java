package com.wxpay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.AbstractWxpayRentBillRequest;
import com.wxpay.api.constant.WxpayApiAddress;
import com.wxpay.api.response.WxpayCreateRentBillResponse;
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
public class WxpayCreateRentBillRequest extends AbstractWxpayRentBillRequest<WxpayCreateRentBillResponse> {
    /**
     * 商户服务订单号
     */
    @JSONField(name = "out_order_no")
    private String outOrderNo;

    /**
     * 商品名称
     */
    @JSONField(name = "goods_name")
    private String goodsName;

    /**
     * 租用时间
     */
    @JSONField(name = "start_time")
    private String startTime;

    /**
     * 预定归还时间
     */
    @JSONField(name = "end_time")
    private String endTime;

    /**
     * 租用地点
     */
    @JSONField(name = "service_location")
    private String serviceLocation;

    /**
     * 押金总额
     */
    @JSONField(name = "deposit_amount")
    private Integer depositAmount;

    /**
     * 租金规则 计费单位
     */
    @JSONField(name = "rent_unit")
    private String rentUnit;

    /**
     * 租金规则 计费单价费用
     */
    @JSONField(name = "rent_unit_fee")
    private Integer rentUnitFee;

    /**
     * 租金规则 计费说明
     */
    @JSONField(name = "rent_fee_desc")
    private String rentFeeDesc;

    /**
     * 商户数据包
     */
    @JSONField(name = "attach")
    private String attach;

    /**
     * 平台证书序列号
     */
    @JSONField(name = "cert_serial_no")
    private String certSerialNo;

    /**
     * 用户标识
     */
    @JSONField(name = "openid")
    private String openid;

    /**
     * 用户姓名
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 用户手机号
     */
    @JSONField(name = "phone")
    private String phone;

    /**
     * 用户身份证ID
     */
    @JSONField(name = "userid")
    private String userid;

    /**
     * 用户收货地址
     */
    @JSONField(name = "address")
    private String address;

    /**
     * 获取请求地址
     *
     * @return 请求地址
     */
    @Override
    public String gainApiRequestAddr() {
        return WxpayApiAddress.CREATE_RENT_BILL;
    }

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    @Override
    public Class<WxpayCreateRentBillResponse> gainResponseClass() {
        return WxpayCreateRentBillResponse.class;
    }

    @Override
    public String gainLogPre() {
        return "微信支付分创建";
    }
}
