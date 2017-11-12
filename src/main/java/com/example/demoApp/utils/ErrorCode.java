package com.example.demoapp.utils;

import java.util.Random;

public class ErrorCode {

    private static final String LETTERS_AND_NUMBERS="0123456789ABCDEFGHJKMNOPRSTUWXYZ";

    private static final Random RN = new Random();
    private static final int MAX = LETTERS_AND_NUMBERS.length()-1;
    private static final int MIN = 0;

    public static String generate(){

        StringBuilder errorCode = new StringBuilder();
        int index;
        for(int i=0; i<6; i++)
        {
            index = RN.nextInt(MAX - MIN + 1) + MIN;
            errorCode = new StringBuilder(errorCode.toString() + LETTERS_AND_NUMBERS.charAt(index) );
        }
        return errorCode.toString();
    }
}
