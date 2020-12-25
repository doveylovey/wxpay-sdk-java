package com.wxpay.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxpay.api.constant.WxpayApiCode;
import com.wxpay.api.constant.WxpayResponseErrCode;
import com.wxpay.api.response.WxpayRefundQueryResponse;
import com.wxpay.api.util.WxpaySignature;
import com.wxpay.api.util.XMLParser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.UUID;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Slf4j
@Data
public abstract class AbstractWxpayClient implements WxpayClient {
    private String appid;

    private String mchId;

    private String key;

    private String certPath;

    private String certPassword;

    private String apiv3key;

    private String serviceId;

    private String version;

    //请求器的配置
    private RequestConfig requestConfig;

    //连接超时时间，默认10秒
    private int socketTimeout = 3000;

    //传输超时时间，默认30秒
    private int connectTimeout = 6000;

    private int connectionRequestTimeout = 8000;

    /**
     * HTTP请求器,禁止直接调用
     */
    private CloseableHttpClient httpClient;

    private Integer initExeNum = 1;

    private Integer maxExeNum = 2;

    private CloseableHttpClient getHttpClient() throws Exception {
        if (httpClient == null) {
            synchronized (CloseableHttpClient.class) {
                //二次检查
                if (httpClient == null) {
                    initHttpClient();
                }
            }
        }
        return httpClient;
    }

    @Override
    public <T extends WxpayResponse> T executeRentBill(AbstractWxpayRentBillRequest<T> request) throws WxpayApiException {
        request.setVersion(version);
        request.setServiceId(serviceId);
        if (StringUtils.isEmpty(request.getSignType())) {
            request.setSignType(WxpaySignature.SIGN_TYPE_HMAC_SHA256);
        }
        return execute(request);
    }

    @Override
    public <T extends WxpayResponse> T execute(WxpayRequest<T> request) throws WxpayApiException {
        return execute(request, initExeNum);
    }

    @Override
    public <T extends WxpayResponse> T execute(WxpayRequest<T> request, Integer exeNum) throws WxpayApiException {
        if (StringUtils.isEmpty(request.getAppid())) {
            request.setAppid(appid);
        }
        request.setMchId(mchId);
        request.setNonce_str(UUID.randomUUID().toString().replace("-", ""));
        if (StringUtils.isEmpty(request.getSignType())) {
            request.setSignType(WxpaySignature.SIGN_TYPE_MD5);
        }
        try {
            String sign = WxpaySignature.getSign(request, key, request.getSignType());
            request.setSign(sign);
        } catch (Exception e) {
            throw new WxpayApiException("签名异常", e);
        }
        String serializable = JSON.toJSONString(request);
        JSONObject requestObj = JSON.parseObject(serializable);
        String postDataXml = XMLParser.object2XML(requestObj);
        try {
            T response = null;
            String responseStr = post(request.gainLogPre(), request.gainApiRequestAddr(), postDataXml);
            if (StringUtils.isNotEmpty(responseStr)) {
                response = parseWxpayResponse(responseStr, request.gainResponseClass());
                if (WxpayApiCode.RETURN_CODE_SUCCESS.equals(response.getReturnCode()) && !WxpayResponseErrCode.SYSTEMERROR.equals(response.getErrCode())) {
                    if (!WxpaySignature.checkIsSignValidFromResponseString(responseStr, key, request.getSignType())) {
                        throw new WxpayApiException("返回值签名校验失败");
                    }
                }
            }
            if (exeNum < maxExeNum && (response == null || WxpayResponseErrCode.SYSTEMERROR.equals(response.getErrCode()))) {
                response = execute(request, exeNum + 1);
            }
            log.info("xml parse obj ----> {}", JSON.toJSONString(response));
            return response;
        } catch (Exception e) {
            throw new WxpayApiException("请求异常", e);
        }
    }

    public <T extends WxpayResponse> T parseWxpayResponse(String xmlStr, Class<T> clazz) throws WxpayApiException {
        if (StringUtils.isEmpty(xmlStr)) {
            return null;
        }
        try {
            JSONObject jsonObject = XMLParser.getJSONObjectFromXML(xmlStr);
            if (WxpayRefundQueryResponse.class == clazz) {
                return WxpayRefundQueryResponse.parseHistoryFromXml(jsonObject, clazz);
            }
            return jsonObject.toJavaObject(clazz);
        } catch (Exception e) {
            throw new WxpayApiException("参数解析异常", e);
        }
    }

    private String post(String logPre, String apiRequestAddr, String postDataXml) {
        String postId = UUID.randomUUID().toString();
        String domian = DomainSelector.getInstance().getDomain();
        String url = domian + apiRequestAddr;
        HttpPost httpPost = new HttpPost(url);
        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXml, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);
        //设置请求器的配置
        httpPost.setConfig(requestConfig);
        String result = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            CloseableHttpClient client = getHttpClient();
            log.info("{}请求[{}] -> url:{},postDataXML:{}", logPre, postId, apiRequestAddr, postDataXml);
            Long start = System.currentTimeMillis();
            response = client.execute(httpPost);
            entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            Long end = System.currentTimeMillis();
            log.info("{}请求结果[{}] [耗时ms:{}] -> url:{}, result:{}", logPre, postId, end - start, url, result);
        } catch (Exception e) {
            httpPost.abort();
            DomainSelector.getInstance().reportErrorDomain(domian);
            log.error("{}请求异常[{}] -> url:{}, postDataXml:{}, result:{}", logPre, postId, url, postDataXml, result, e);
        } finally {
            try {
                EntityUtils.consume(entity);
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("关闭连接时异常");
            }
        }
        return result;
    }

    private void initHttpClient() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //加载本地的证书进行https加密传输
        try (FileInputStream instream = new FileInputStream(new File(this.certPath))) {
            //设置证书密码
            keyStore.load(instream, this.certPassword.toCharArray());
        }
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, this.certPassword.toCharArray()).build();
        // 客户端验证服务器身份的策略
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//        sslsf = HttpsUt
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
        httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
}
