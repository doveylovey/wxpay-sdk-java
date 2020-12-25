package com.wxpay.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
public abstract class AbstractWxpayRentBillRequest<T extends WxpayResponse> extends WxpayRequest<T> {
    /**
     * 接口版本号
     */
    private String version;

    /**
     * 服务ID
     */
    @JSONField(name = "service_id")
    private String serviceId;
}
