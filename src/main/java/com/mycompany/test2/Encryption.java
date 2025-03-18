package com.mycompany.test2;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encryption {

    public static String Encrypt(String password) throws Exception {
        return Encrypt(password,1);
    }
    public static Boolean CheckCorrectness(String password,String hashedPassword) throws Exception {
        return CheckCorrectness(password,1,hashedPassword);
    }

    public static String Encrypt(String password,int type) throws Exception {
        String hashType = "SHA-512";
        switch (type) {
            case 1 -> hashType = "SHA-512";
            case 2 -> hashType = "SHA-384";
            case 3 -> hashType = "SHA-256";
        }
        byte[] MDdigest = MessageDigest.getInstance(hashType).digest(password.getBytes());
        return new BigInteger(1, MDdigest).toString();
    }

    public static Boolean CheckCorrectness(String password,int type,String hashedPassword) throws Exception {
        return Encrypt(password,type).equals(hashedPassword);
    }
}
