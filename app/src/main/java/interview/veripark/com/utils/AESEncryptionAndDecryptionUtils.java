package interview.veripark.com.utils;

import android.os.Build;
import android.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by mertKaradeniz on 6.11.2021
 * <p>
 * This is an interview project.
 */

public class AESEncryptionAndDecryptionUtils {


    public static String encrypt(String aesKey, String aesVI, String text) {
        Security.addProvider(new BouncyCastleProvider());

        byte[] aesKeyBytes = Base64.decode(aesKey, Base64.DEFAULT);
        byte[] aesVIBytes = Base64.decode(aesVI, Base64.DEFAULT);
        byte[] textBytes = text.getBytes();

        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKeyBytes, "AES");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(aesVIBytes);

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        byte[] cipherText = new byte[cipher.getOutputSize(textBytes.length)];

        String outputBase64 = "";
        try {
            outputBase64 = Base64.encodeToString(cipher.doFinal(cipherText), Base64.CRLF);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return outputBase64;
    }


    public static String decrypt(String aesKey, String aesVI, String text) {
        Security.addProvider(new BouncyCastleProvider());

        byte[] aesKeyBytes = Base64.decode(aesKey, Base64.DEFAULT);
        byte[] aesVIBytes = Base64.decode(aesVI, Base64.DEFAULT);
        byte[] textBytes = text.getBytes();

        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKeyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(aesVIBytes);


        int ctLength = 0;
        Cipher cipher = null;

        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
        int ptLength = 0;

        byte[] cipherText = new byte[cipher.getOutputSize(textBytes.length)];

        try {
            ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
        } catch (ShortBufferException e) {
            e.printStackTrace();
        }

        try {
            ptLength += cipher.doFinal(plainText, ptLength);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            e.printStackTrace();
        }

        return new String(plainText);
    }
}
