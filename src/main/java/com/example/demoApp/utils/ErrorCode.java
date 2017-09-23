package com.example.demoApp.utils;

import java.util.Random;

public class ErrorCode {

    private final static String LETTERS_AND_NUMBERS="0123456789ABCDEFGHJKMNOPRSTUWXYZ";

    private static Random rn = new Random();
    private static int max = LETTERS_AND_NUMBERS.length();
    private static int min = 0;

    public static String generate(){

        String errorCode="";
        int index;
        for(int i=0; i<6; i++)
        {
            index = rn.nextInt(max - min + 1) + min;
            errorCode = errorCode + LETTERS_AND_NUMBERS.charAt(index);
        }
        return errorCode;
    }
}
