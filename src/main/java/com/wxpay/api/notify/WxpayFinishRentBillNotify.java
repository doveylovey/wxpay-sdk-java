package com.wxpay.api.notify;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxpay.api.util.AESGcmUtil;
import com.wxpay.api.util.XMLParser;
import lombok.Data;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
public class WxpayFinishRentBillNotify {
    /**
     * 商户号
     */
    @JSONField(name = "mch_id")
    private String mchId;

    /**
     * 公众账号
     */
    @JSONField(name = "appid")
    private String appid;

    /**
     * 通知ID
     */
    @JSONField(name = "event_id")
    private String eventId;

    /**
     * 通知创建时间
     */
    @JSONField(name = "event_create_time")
    private String eventCreateTime;

    /**
     * 通知类型
     */
    @JSONField(name = "event_type")
    private String eventType;

    /**
     * 通知加密类型
     */
    @JSONField(name = "event_algorithm")
    private String eventAlgorithm;

    /**
     * 通知随机串
     */
    @JSONField(name = "event_nonce")
    private String eventNonce;

    /**
     * 通知附加数据
     */
    @JSONField(name = "event_associated_data")
    private String eventAssociatedData;

    /**
     * 通知密文
     */
    @JSONField(name = "event_ciphertext")
    private String eventCiphertext;

    /**
     * 签名算法
     */
    @JSONField(name = "algorithm")
    private String algorithm;

    /**
     * 随机串
     */
    @JSONField(name = "nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    @JSONField(name = "sign")
    private String sign;

    /**
     * 解密密文
     *
     * @return
     */
    public WxpayFinishRentBillNotifyPlaintext decrypt(String apiv3Key) throws Exception {
        String decryptText = AESGcmUtil.aesGcmDecrypt(apiv3Key, eventAssociatedData, eventNonce, eventCiphertext);
        return XMLParser.getObjFromXML(decryptText, WxpayFinishRentBillNotifyPlaintext.class);
    }
}
