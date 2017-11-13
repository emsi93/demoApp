package com.example.demoapp.email;

import com.example.demoapp.configuration.Config;
import com.example.demoapp.utils.TokenUtil;

public class EmailActivation extends Email {

    private static final String MAPPING = "/activeUser/active?";
    private static final String ACTIVE_LINK_TYPE = "ACTIVATION";

    public EmailActivation(String emailAddress, String host) {
        url = host + MAPPING + Config.TOKEN_PARAM + "=" + TokenUtil.generateToken();
        topic = "Activation account";
        recipient = emailAddress;
        type = ACTIVE_LINK_TYPE;
    }
}
