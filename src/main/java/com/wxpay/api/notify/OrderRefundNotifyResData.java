package com.wxpay.api.notify;

import com.wxpay.api.util.AESUtil;
import com.wxpay.api.util.XMLParser;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 订单退款回调接收类。已经废弃，请使用{@link WxpayRefundNotify}
 *
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
@Deprecated
public class OrderRefundNotifyResData {
    private String return_code; //SUCCESS/FAIL
    private String return_msg;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String req_info;
    private int status;

    private String transaction_id; //微信订单号
    private String out_trade_no; //商户订单号
    private String refund_id; //微信退款单号
    private String out_refund_no; //商户退款单号
    private int total_fee; //订单金额
    private int refund_fee;//申请退款金额
    private int settlement_refund_fee;  //退款金额
    private String refund_status;//退款状态
    private String success_time; //成功退款时间
    private String refund_recv_accout; //退款入账账户
    private String refund_account; //退款资金来源
    private String refund_request_source; //退款发起来源

    /**
     * 解析加密数据
     *
     * @param key
     * @return
     * @throws Exception
     */
    public OrderRefundNotifyResData parse(String key) throws Exception {
        if (!isReqSuccess()) {
            return null;
        }
        String text = AESUtil.decryptData(req_info, key);
        OrderRefundNotifyResData notify = (OrderRefundNotifyResData) XMLParser.getObjectFromXml(text, OrderRefundNotifyResData.class, "root");
        notify.setReturn_code(return_code);
        notify.setReturn_msg(return_msg);
        notify.setAppid(appid);
        notify.setMch_id(mch_id);
        notify.setNonce_str(nonce_str);
        notify.setReq_info(req_info);
        notify.isRefundSuccess();
        return notify;
    }

    public boolean isReqSuccess() {
        if (StringUtils.isNotEmpty(return_code) && "SUCCESS".equals(return_code)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRefundSuccess() {
        if (StringUtils.isNotEmpty(refund_status)) {
            if ("SUCCESS".equals(refund_status)) {
                this.status = 2;
                return true;
            } else {
                this.status = 3;
            }
        }
        return false;
    }
}


