package com.nemboru.nemboru.proto2;

import android.util.Base64;
import android.util.Log;

import org.bouncycastle.crypto.generators.BCrypt;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.security.Security;
/**
 * Created by nemboru on 12/11/16.
 */

public class PasswordDerivation {

    public static byte[] derive(String key){
        Security.addProvider(new BouncyCastleProvider());
        try {
            byte[] toret = BCrypt.generate(key.getBytes("UTF-8"),"Securer Securer!".getBytes("UTF-8"),4);
            return toret;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
