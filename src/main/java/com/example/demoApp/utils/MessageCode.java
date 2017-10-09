package com.example.demoApp.utils;

public class MessageCode {

    public static int getMessageCode(Integer messsageCode) {
        int code=0;
        if (messsageCode != null) {
            switch (messsageCode) {
                case 1:
                    code = 1;
                    break;
                case 2:
                    code = 2;
                    break;
                default:
                    break;
            }
        }
        return code;
    }
}
