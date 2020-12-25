package com.wxpay.api;

import lombok.Data;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
public class DefaultWxpayClient extends AbstractWxpayClient {
    public DefaultWxpayClient(String appid, String mchId, String key, String certPath, String certPassword, String apiv3key) {
        this.setAppid(appid);
        this.setMchId(mchId);
        this.setKey(key);
        this.setCertPath(certPath);
        this.setCertPassword(certPassword);
        this.setApiv3key(apiv3key);
    }

    public DefaultWxpayClient(String appid, String mchId, String key, String certPath, String certPassword, String apiv3key, String serviceId, String version) {
        this.setAppid(appid);
        this.setMchId(mchId);
        this.setKey(key);
        this.setCertPath(certPath);
        this.setCertPassword(certPassword);
        this.setApiv3key(apiv3key);
        this.setServiceId(serviceId);
        this.setVersion(version);
    }
}
