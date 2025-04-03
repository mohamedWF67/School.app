package com.mycompany.School_app;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    //Encrypt password using SHA-512,SHA-384 or SHA-256
    public static String Encrypt(String password,int type)  {
        String hashType = "SHA-512";
        switch (type) {
            case 1 -> hashType = "SHA-512";
            case 2 -> hashType = "SHA-384";
            case 3 -> hashType = "SHA-256";
        }
        //Degest password to byte array with SHA hashing
        try {
            byte[] MDdigest = MessageDigest.getInstance(hashType).digest(password.getBytes());
            return new BigInteger(1, MDdigest).toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //Checks if the password is equal to a hashed password
    public static Boolean CheckCorrectness(String password,int type,String hashedPassword) {
        return Encrypt(password,type).equals(hashedPassword);
    }

    //Encryption using SHA-512
    public static String Encrypt(String password) {
        return Encrypt(password,1);
    }

    //Checks if the password is equal to a SHA-512 hashed password
    public static Boolean CheckCorrectness(String password,String hashedPassword)  {
        return CheckCorrectness(password,1,hashedPassword);
    }
}
