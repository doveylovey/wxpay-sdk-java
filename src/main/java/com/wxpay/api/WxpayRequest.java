package com.wxpay.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public abstract class WxpayRequest<T extends WxpayResponse> {
    private String appid;

    @JSONField(name = "mch_id")
    private String mchId;

    @JSONField(name = "nonce_str")
    private String nonce_str;

    private String sign;

    @JSONField(name = "sign_type")
    private String signType;

    /**
     * 获取请求地址
     *
     * @return 请求地址
     */
    @JSONField(serialize = false)
    public abstract String gainApiRequestAddr();

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    @JSONField(serialize = false)
    public abstract Class<T> gainResponseClass();

    @JSONField(serialize = false)
    public abstract String gainLogPre();
}
