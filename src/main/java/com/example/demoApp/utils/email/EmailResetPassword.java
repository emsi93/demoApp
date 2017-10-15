package com.example.demoApp.utils.email;

import com.example.demoApp.configuration.Config;
import com.example.demoApp.utils.TokenUtil;

public class EmailResetPassword extends Email {

    private final static String MAPPING = "/password/resetPassword?";
    private final static String TOPIC = "Reset password";
    private final static String RESET_LINK_TYPE = "RESET";

    public EmailResetPassword(String emailAddress, String host){
        url = host + MAPPING + Config.TOKEN_PARAM + "=" + TokenUtil.generateToken();
        topic = TOPIC;
        recipient = emailAddress;
        type = RESET_LINK_TYPE;
    }
}
