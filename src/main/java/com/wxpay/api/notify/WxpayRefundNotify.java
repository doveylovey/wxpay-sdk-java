package com.wxpay.api.notify;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.constant.WxpayApiCode;
import com.wxpay.api.util.AESUtil;
import com.wxpay.api.util.XMLParser;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@NoArgsConstructor
@ToString
public class WxpayRefundNotify implements Serializable {
    @JSONField(name = "return_code")
    private String returnCode;

    @JSONField(name = "return_msg")
    private String return_msg;

    @JSONField(name = "appid")
    private String appid;

    @JSONField(name = "mch_id")
    private String mchId;

    @JSONField(name = "nonce_str")
    private String nonceStr;

    @JSONField(name = "req_info")
    private String reqInfo;

    private WxpayRefundNotifyPlaintext notifyPlaintext;

    /**
     * 解密密文
     *
     * @return
     */
    public WxpayRefundNotifyPlaintext decrypt(String key) throws Exception {
        if (!isReqSuccess()) {
            return null;
        }
        String text = AESUtil.decryptData(this.reqInfo, key);
        notifyPlaintext = XMLParser.getObjFromXML(text, WxpayRefundNotifyPlaintext.class);
        return notifyPlaintext;
    }

    public boolean isReqSuccess() {
        return (StringUtils.isNotEmpty(this.returnCode) && WxpayApiCode.RETURN_CODE_SUCCESS.equals(this.returnCode));
    }
}
