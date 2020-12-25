package com.wxpay.api;

import com.alibaba.fastjson.JSON;
import com.wxpay.api.notify.WxpayRefundNotify;
import com.wxpay.api.notify.WxpayRefundNotifyPlaintext;
import com.wxpay.api.notify.WxpayUnifiedOrderNotify;
import com.wxpay.api.request.WxpayCreateRentBillRequest;
import com.wxpay.api.request.WxpayFinishRentBillRequest;
import com.wxpay.api.request.WxpayOrderQueryRequest;
import com.wxpay.api.request.WxpayQueryRentBillRequest;
import com.wxpay.api.request.WxpayRefundQueryRequest;
import com.wxpay.api.request.WxpayRefundRequest;
import com.wxpay.api.request.WxpayUnifiedOrderRequest;
import com.wxpay.api.response.WxpayCreateRentBillResponse;
import com.wxpay.api.response.WxpayFinishRentBillResponse;
import com.wxpay.api.response.WxpayOrderQueryResponse;
import com.wxpay.api.response.WxpayQueryRentBillResponse;
import com.wxpay.api.response.WxpayRefundQueryResponse;
import com.wxpay.api.response.WxpayRefundResponse;
import com.wxpay.api.response.WxpayUnifiedOrderResponse;
import com.wxpay.api.util.WxpaySignature;
import com.wxpay.api.util.XMLParser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付请求 demo，各接口请求参数请参照微信支付文档
 * 微信支付文档：https://pay.weixin.qq.com/wiki/doc/api/index.html
 * 微信支付分文档：https://pay.weixin.qq.com/wiki/doc/apiv3/payscore.php?chapter=18_1&index=2
 */
public class WxpayTests {
    Logger log = LoggerFactory.getLogger(WxpayTests.class);

    static WxpayClient client = new DefaultWxpayClient(
            WxPayTestsConfig.appId,
            WxPayTestsConfig.merId,
            WxPayTestsConfig.key,
            WxPayTestsConfig.certPath,
            WxPayTestsConfig.certPassword,
            WxPayTestsConfig.apiv3key,
            WxPayTestsConfig.serviceId,
            WxPayTestsConfig.defaultVersion
    );

    /**
     * 微信统一下单接口（以小程序支付为例）
     *
     * @throws Exception
     */
    @Test
    public void unifiedOrder() throws Exception {
        WxpayUnifiedOrderRequest request = new WxpayUnifiedOrderRequest();
        request.setBody("小程序支付测试");
        request.setOutTradeNo("test123456789");
        request.setTotalFee(100);
        request.setSpbillCreateIp("127.0.0.1");
        request.setNotifyUrl("http://baidu.com");
        request.setTradeType("JSAPI");
        request.setOpenid("okH_i5KKy2RWuXxxxxxxxxxxxx");
        WxpayUnifiedOrderResponse response = client.execute(request);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 订单查询
     *
     * @throws Exception
     */
    @Test
    public void query() throws Exception {
        WxpayOrderQueryRequest request = new WxpayOrderQueryRequest();
        request.setOutTradeNo("1429182773xxxxxxxx");
        WxpayOrderQueryResponse response = client.execute(request);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 退款
     *
     * @throws Exception
     */
    @Test
    public void refund() throws Exception {
        WxpayRefundRequest request = new WxpayRefundRequest();
        request.setOutTradeNo("1397889448xxxxxxxx");
        request.setOutRefundNo("1397889448xxxxxxxx");
        request.setTotalFee(50);
        request.setRefundFee(20);
        WxpayRefundResponse response = client.execute(request);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 退款查询
     *
     * @throws Exception
     */
    @Test
    public void refundQuery() throws Exception {
        WxpayRefundQueryRequest request = new WxpayRefundQueryRequest();
        request.setOutTradeNo("1397889448xxxxxxxx");
        WxpayRefundQueryResponse response = client.execute(request);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 支付成功回调
     * KEY 是解密密钥
     **/
    /*@ResponseBody
    @RequestMapping(value = "/create")
    public String create(@RequestBody String notifyData) {
        log.info("微信支付回调 -->" + notifyData);
        try {
            if (!WxpaySignature.checkIsSignValidFromResponseString(notifyData, KEY, WxpaySignature.SIGN_TYPE_MD5)) {
                return returnFail("签名校验失败");
            }
        } catch (Exception e) {
            log.error("微信支付回调(deposit:{}):异常1", notifyData, e);
            return returnFail("签名校验异常");
        }
        WxpayUnifiedOrderNotify notify = XMLParser.getObjFromXML(notifyData, WxpayUnifiedOrderNotify.class);
        if (notify.isSuccess()) {
            //todo 支付成功后逻辑
        }
        return returnSuccess();
    }*/

    /**
     * 退款回调（微信和微信分是同一个回调地址）
     */
    /*@ResponseBody
    @RequestMapping(value = "/refund")
    public String refund(@RequestBody String notifyData) {
        log.info("微信退款回调 notify -------------->{}", notifyData);
        WxpayRefundNotifyPlaintext notifyPlaintext = null;
        try {
            WxpayRefundNotify notify = XMLParser.getObjFromXML(notifyData, WxpayRefundNotify.class);
            notifyPlaintext = notify.decrypt(KEY);
            log.info("refund notify2 text -------------->{}", JSON.toJSONString(notify));
            if (notifyPlaintext != null && notifyPlaintext.isSuccess()) {
                log.info("退款回调解密：{}", JSON.toJSONString(notifyPlaintext));
                //todo 退款成功后逻辑
            }
        } catch (Exception e) {
            log.error("微信退款回调异常:{},明文：notifyPlaintext：{}", notifyData, JSON.toJSONString(notifyPlaintext), e);
            return returnFail("退款解析异常");
        }
        return returnSuccess();
    }*/

    protected String returnSuccess() {
        return returnStr(true, "成功");
    }

    protected String returnFail(String msg) {
        return returnStr(true, msg);
    }

    protected String returnStr(boolean success, String msg) {
        Map<String, Object> re = new HashMap<>();
        re.put("return_code", success ? "SUCCESS" : "FAIL");
        re.put("return_msg", msg);
        return XMLParser.object2XML(re);
    }

    /************************************** 支付分 begin ********************************************/

    @Test
    public void creatRentBill() throws Exception {
        WxpayCreateRentBillRequest request = new WxpayCreateRentBillRequest();
        request.setOutOrderNo("test123421");
        request.setGoodsName("测试创建租借订单");
        request.setDepositAmount(10);
        request.setRentUnit("FEN_1_HOUR");
        request.setRentUnitFee(10);
        WxpayCreateRentBillResponse response = client.executeRentBill(request);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 支付分-免押租借-查询
     */
    @Test
    public void queryRentBill() throws Exception {
        WxpayQueryRentBillRequest request = new WxpayQueryRentBillRequest();
        request.setOutOrderNo("153765654xxxxxxxx");
        WxpayQueryRentBillResponse response = client.executeRentBill(request);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 支付分-免押租借-完结
     */
    @Test
    public void finishRentBill() throws Exception {
        WxpayFinishRentBillRequest request = new WxpayFinishRentBillRequest();
        request.setOutOrderNo("1562640431xxxxxxxx");
        request.setReturned("TRUE");
        request.setRealEndTime("20190812095900");
        request.setTotalAmount(10);
        request.setRentFee(10);
        request.setFinishTicket("JTYJmxfGyRCV2FqJlI%2FoPulXWeoYbplkh5MihbuHcrQ94l2FpGpCD0Cxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        WxpayFinishRentBillResponse response = client.executeRentBill(request);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 支付分-免押租借-退款
     */
    @Test
    public void refundRentBill() throws Exception {
        WxpayRefundRequest request = new WxpayRefundRequest();
        request.setTransactionId("42000003302019053197xxxxxxxx");
        request.setOutRefundNo("1495025867460xxxxxxx");
        request.setTotalFee(100);
        request.setRefundFee(10);
        WxpayRefundResponse response = client.execute(request);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 支付分-免押租借-退款查询
     *
     * @throws Exception
     */
    @Test
    public void refundRentBillQuery() throws Exception {
        WxpayRefundQueryRequest request = new WxpayRefundQueryRequest();
        request.setTransactionId("42000003302019053xxxxxxxxxxxx");
        WxpayRefundQueryResponse response = client.execute(request);
        log.info(JSON.toJSONString(response));
    }
}
