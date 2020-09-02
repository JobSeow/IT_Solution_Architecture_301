package com.itsa.gourds.masks;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;

public class Encryption {
    static String ALGO = "AES/CBC/PKCS5PADDING";
    private static final byte[] key_Array = {1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1, 7, 7, 7, 7 };
    private static final byte[] iv = { 1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1, 7, 7, 7, 7 };

    public static String aesEncrypt(String data) {
        String cipherText = "";
        try {
            Cipher _Cipher = Cipher.getInstance(ALGO);

            // Initialization vector.
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            Key SecretKey = new SecretKeySpec(key_Array, "AES");
            _Cipher.init(Cipher.ENCRYPT_MODE, SecretKey, ivspec);

            cipherText = Base64.getEncoder().encodeToString(_Cipher.doFinal(data.getBytes("ASCII")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

}
