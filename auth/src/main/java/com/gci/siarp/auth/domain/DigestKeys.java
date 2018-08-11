package com.gci.siarp.auth.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Leo Bethancourt
 */
public class DigestKeys {

    static MessageDigest md = null;
    static String returnKey = null;

    public static String CryptSHA512(String key) {
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(key.getBytes());
            byte[] mb = md.digest();
            //System.out.println(Hex.encodeHex(mb));
            returnKey = Hex.encodeHexString(mb);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DigestKeys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnKey;
    }
    
    public static String CryptSHA1(String key) {
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(key.getBytes());
            byte[] mb = md.digest();
            //System.out.println(Hex.encodeHex(mb));
            returnKey = Hex.encodeHexString(mb);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DigestKeys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnKey;
    }
}
