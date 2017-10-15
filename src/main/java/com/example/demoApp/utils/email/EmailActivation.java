package com.example.demoApp.utils.email;

import com.example.demoApp.configuration.Config;
import com.example.demoApp.utils.TokenUtil;

public class EmailActivation extends Email {

    private final static String MAPPING = "/activeUser/active?";
    private final static String TOPIC = "Activation account";
    private final static String ACTIVE_LINK_TYPE = "ACTIVATION";

    public EmailActivation(String emailAddress, String host) {
        url = host + MAPPING + Config.TOKEN_PARAM + "=" + TokenUtil.generateToken();
        topic = TOPIC;
        recipient = emailAddress;
        type = ACTIVE_LINK_TYPE;
    }
}
