package interview.veripark.com.utils;

import org.bouncycastle.util.encoders.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by mertKaradeniz on 8.11.2021
 * <p>
 * This is an interview project.
 */

public class AESUtils {

    private static final String HEXES = "0123456789ABCDEF";

    public static byte[] encrypt(String aesKey, String aesVI, String plainText) throws Exception {
        byte[] aesKeyBytes = android.util.Base64.decode(aesKey, android.util.Base64.DEFAULT);
        byte[] aesVIBytes = android.util.Base64.decode(aesVI, android.util.Base64.DEFAULT);

        SecretKey key = new SecretKeySpec(aesKeyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(aesVIBytes);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());

        return cipherText;
    }

    public static String decrypt(String aesKey, String aesVI, byte[] cipherText) throws Exception {
        byte[] aesKeyBytes = android.util.Base64.decode(aesKey, android.util.Base64.DEFAULT);
        byte[] aesVIBytes = android.util.Base64.decode(aesVI, android.util.Base64.DEFAULT);

        SecretKey key = new SecretKeySpec(aesKeyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(aesVIBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decryptedText = cipher.doFinal(cipherText);

        LogKey(aesKey, aesVI, new String(decryptedText));
        return new String(decryptedText);
    }

    public static String converterByteToString(byte[] bytes) {
        Base64 codec = new Base64();
        byte[] encoded = codec.encode(bytes);
        return new String(encoded);
    }

    static void LogKey(String aesKey, String aesVI, String output) {
        byte[] aesKeyBytes = android.util.Base64.decode(aesKey, android.util.Base64.DEFAULT);
        byte[] aesVIBytes = android.util.Base64.decode(aesVI, android.util.Base64.DEFAULT);

        System.out.println("aesKeyBytes: " + getHex(aesKeyBytes));
        System.out.println("aesVIBytes: " + getHex(aesVIBytes));
        System.out.println("output: " + output);
    }

    static String getHex(byte[] raw) {
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

}
