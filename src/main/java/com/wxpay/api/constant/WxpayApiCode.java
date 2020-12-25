package com.wxpay.api.constant;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public interface WxpayApiCode {
    /**
     * 返回状态码
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    String RETURN_CODE_SUCCESS = "SUCCESS";

    String RETURN_CODE_FAIL = "FAIL";

    /**
     * 业务结果
     */
    String RESULT_CODE_SUCCESS = "SUCCESS";

    String REFUND_SUCCESS = "SUCCESS";
}
