package com.example.demoapp.utils.email;

import com.example.demoapp.configuration.Config;
import com.example.demoapp.utils.TokenUtil;

public class EmailResetPassword extends Email {

    private static final String MAPPING = "/password/resetPassword?";
    private static final String RESET_LINK_TYPE = "RESET";

    public EmailResetPassword(String emailAddress, String host){
        url = host + MAPPING + Config.TOKEN_PARAM + "=" + TokenUtil.generateToken();
        topic = "Reset password";
        recipient = emailAddress;
        type = RESET_LINK_TYPE;
    }
}
