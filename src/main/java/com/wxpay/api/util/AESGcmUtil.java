package com.wxpay.api.util;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AEAD_AES_256_GCM算法加解密工具类
 *
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public class AESGcmUtil {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int NONCE_LENGTH_BYTE = 12;
    private static final String TRANSFORMATION_PKCS1Padding = "RSA/ECB/PKCS1Padding";
    private static final String AES = "AES";

    public static String aesGcmDecrypt(String key, String aad, String iv, String cipherText) throws Exception {
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), AES);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, spec);
        cipher.updateAAD(aad.getBytes());
        return new String(cipher.doFinal(Base64Util.decryptBASE64(cipherText)));
    }
}
