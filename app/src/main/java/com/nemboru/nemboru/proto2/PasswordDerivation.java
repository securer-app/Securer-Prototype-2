package com.nemboru.nemboru.proto2;

import android.util.Base64;
import android.util.Log;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.generators.BCrypt;
import org.bouncycastle.jcajce.provider.util.DigestFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
/**
 * Created by nemboru on 12/11/16.
 */

public class PasswordDerivation {

    public static byte[] derive(String key){
        Security.addProvider(new BouncyCastleProvider());
        try {
            Log.d("DERIVING",key);
            byte[] toret = BCrypt.generate(key.getBytes("UTF-8"),"Securer Securer!".getBytes("UTF-8"),4);
            Log.d("Derived", Base64.encodeToString(toret,Base64.DEFAULT));
            return toret;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
