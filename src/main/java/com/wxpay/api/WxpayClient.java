package com.wxpay.api;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public interface WxpayClient {
    /**
     * 请求
     *
     * @param request
     * @param <T>
     * @return
     * @throws WxpayApiException
     */
    <T extends WxpayResponse> T executeRentBill(AbstractWxpayRentBillRequest<T> request) throws WxpayApiException;

    /**
     * 请求
     *
     * @param request
     * @param <T>
     * @return
     * @throws WxpayApiException
     */
    <T extends WxpayResponse> T execute(WxpayRequest<T> request) throws WxpayApiException;

    /**
     * 请求
     *
     * @param request
     * @param exeNum
     * @param <T>
     * @return
     * @throws WxpayApiException
     */
    <T extends WxpayResponse> T execute(WxpayRequest<T> request, Integer exeNum) throws WxpayApiException;
}
