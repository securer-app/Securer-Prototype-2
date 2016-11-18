package com.nemboru.nemboru.proto2;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by nemboru on 12/11/16.
 */

public class AEScrypt {
    public static Cipher cipher;

    public static final byte[] encrypt(byte[] key, byte[] payload) {
        try {
            return work(key, payload, true);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final byte[] decrypt(byte[] key, byte[] payload) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        return work(key, payload, false);
    }

    public static final byte[] work(byte[] key, byte[] payload,boolean encrypt) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Security.addProvider(new BouncyCastleProvider());

        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        Key parsedKey = new SecretKeySpec(key, "AES");
        if (encrypt) {
            cipher.init(Cipher.ENCRYPT_MODE, parsedKey);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, parsedKey);
        }
        return cipher.doFinal(payload);
    }
}
