package com.nemboru.nemboru.proto2;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 * Created by nemboru on 12/11/16.
 */

public class AESWrapper {
    public static final String Encrypt(String key, String payload){
        byte[] newkey = PasswordDerivation.derive(key);
        try {
            byte[] cpayload = AEScrypt.encrypt(newkey, payload.toString().getBytes("UTF-8"));
            String printpayload = Base64.encodeToString(cpayload,Base64.DEFAULT);
            return printpayload;
        } catch (UnsupportedEncodingException e) {
            //    e.printStackTrace();
        }
        return "";
    }

    public static final String Decrypt(String key, String payload) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        byte[] newkey = PasswordDerivation.derive(key);
        byte[] content = Base64.decode(payload,Base64.DEFAULT);
        byte[] cpayload = AEScrypt.decrypt(newkey, content);
        try {
            return new String(cpayload,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
