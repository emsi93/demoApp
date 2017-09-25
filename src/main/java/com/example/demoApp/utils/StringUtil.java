package com.example.demoApp.utils;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class StringUtil {

    private static Random random = new Random((new Date()).getTime());

    public static String encrypt(String userId) {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return encoder.encode(salt) + encoder.encode(userId.getBytes());
    }

    public static String decrypt(String encryptKey) {
        if (encryptKey.length() > 12) {
            String cipher = encryptKey.substring(12);
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                return new String(decoder.decodeBuffer(cipher));
            } catch (IOException e) {

            }
        }
        return null;
    }

}
