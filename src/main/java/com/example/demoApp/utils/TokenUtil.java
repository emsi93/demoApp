package com.example.demoApp.utils;


import java.security.SecureRandom;

public class TokenUtil {

    public static String generateToken(){
        SecureRandom random = new SecureRandom();
        long longToken = Math.abs( random.nextLong() );
        String token = Long.toString( longToken, 16 );
        return token;
    }
}