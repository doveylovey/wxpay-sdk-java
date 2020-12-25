package com.wxpay.api.vo;

import com.wxpay.api.constant.WxpayConstant;
import com.wxpay.api.response.WxpayCreateRentBillResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
public class WxScorePullUpPayConsoleVO implements Serializable {
    private String businessType;
    private WxScorePullUpPayConsoleExtraData extraData;

    public WxScorePullUpPayConsoleVO(WxpayCreateRentBillResponse response, String key) throws Exception {
        this.businessType = WxpayConstant.BUSINESS_TYPE;
        this.extraData = new WxScorePullUpPayConsoleExtraData(response, key);
    }
}
