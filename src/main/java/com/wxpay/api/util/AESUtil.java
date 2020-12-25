package com.wxpay.api.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public class AESUtil {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS5Padding";
    /**
     * 生成key
     */
    private static SecretKeySpec key = null;

    private static void createkey(String _key) {
        if (key == null) {
            key = new SecretKeySpec(MD5.encode(_key).toLowerCase().getBytes(), ALGORITHM);
        }

    }

    /**
     * AES加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptData(String data, String _key) throws Exception {
        createkey(_key);
        // 创建密码器  
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        // 初始化  
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64Util.encryptBASE64(cipher.doFinal(data.getBytes()));
    }

    /**
     * AES解密
     *
     * @param base64Data
     * @return
     * @throws Exception
     */
    public static String decryptData(String base64Data, String _key) throws Exception {
        createkey(_key);
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64Util.decryptBASE64(base64Data)));
    }  

    /*public static void main(String[] args) throws Exception {  
        //解密
    	String req_info = "c9MlCsWDi9om84fNJfOET+WxfIL+qvQh8h+Uueuj+gYBvCA/XOxX2ZQQRHmNzTCyzebNVQyKCFFowLh0JvlyVzYzvIafBDPAQyuGQO5fQJzCGaSKKXD0x5WEyeD/lmVpEy8e0efSoTfYOZKJthpYfPC1mZEjC4+2OGZY7hHkUwFo6nIEhnbiPZ6xztZjHutNVKETY5nJkNysjWMQUazBlyu9jcINj4musFOpDcG7FHnFh5WlnwTerivWp2uRwJOe8uOWjrUbda8dMABDE7HlZMuYQo2LMLMcasL4SpiRDu/DJt9k5IExdCBhD6cOVA+aC09RE9B5abKxS0i4MthpYUahfY4SnO0cZKwINiE8JAq0LtU8rCtbMn/QSY6TtXDwDPra19dZcz4FljDHJ/Q1TJOi26r+bI+kyO+xsvaiSYx/cy1MABPChcICoN4i5q0cgKuatC6YKKMEO7WMmX9qcsEQ8BLbt/ZkE6tffJaMN1vZfnnQ93BpHx2dAO2VcL0oxvwOBiLhp2paDfPXPcLk+2NJkXlAnz4//eCjiKAmdPQ9Zpkq9nylypiX/PPjxG/E7K6G+ena1MpOkuzmacrRyiVJwhoODIEu67QRQPTeoKK97Qv2SJSsPvNSUBg74COhHz4MaqOUoe1gfWIVe4Wu7ypqssOhxsZgdJao9PIrv4K8TAPs+r9/H450vtgMdi8QukKIhLvoLPxCV7oIInYFGxH8PjC58mLFWJoyzjkVTtX+3UnbUFxFmoa7/gZivWKd2VdiQFBQf9lW1SWd2O+a92JtJLajwW9WwmrYWmyfI+BhmOTmB3Ni6tqfBo4k3YEE00jFYlA5QrKE7+Xe+AoliGOXmMz2uJA9fuoiQxNBgFZgkDuyUjBwUbBDfzHfBAxLkplp6FOwzHmGxTJ6u3Ul5CTbvoXhLoZzYLm6Fnxur7LkcRUQVW0bZzu3ZjoyNuUEc09vj2zT+ZkUN/furpteymqmtoz82aRhKTrj35Hrmj+Pak0AmDRu/Xe2LmxqGxwSOT0zzk8llyNdwsTn5tpqOQ==";
    	String B = WechatAESUtil.decryptData(req_info,"JCdfeCpxtAw2RPqKBUyyMXuF8aBR36xv");  
        System.out.println(B);
        //加密
        String str = "<root>"+
                "<out_refund_no><![CDATA[2531340110812300]]></out_refund_no>"+
                "<out_trade_no><![CDATA[2531340110812100]]></out_trade_no>"+
                "<refund_account><![CDATA[REFUND_SOURCE_RECHARGE_FUNDS]]></refund_account>"+
                "<refund_fee><![CDATA[1]]></refund_fee>"+
                "<refund_id><![CDATA[50000505542018011003064518841]]></refund_id>"+
                "<refund_recv_accout><![CDATA[支付用户零钱]]></refund_recv_accout>"+
                "<refund_request_source><![CDATA[API]]></refund_request_source>"+
                "<refund_status><![CDATA[SUCCESS]]></refund_status>"+
                "<settlement_refund_fee><![CDATA[1]]></settlement_refund_fee>"+
                "<settlement_total_fee><![CDATA[1]]></settlement_total_fee>"+
                "<success_time><![CDATA[2018-01-10 10:31:24]]></success_time>"+
                "<total_fee><![CDATA[1]]></total_fee>"+
                "<transaction_id><![CDATA[4200000052201801101409025381]]></transaction_id>"+
                "</root>";
        System.out.println(encryptData(str,"JCdfeCpxtAw2RPqKBUyyMXuF8aBR36xv"));
   }  */
}
