package com.example.demoApp.mvc.service.impl;

import com.example.demoApp.mvc.service.EmailServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailServiceInterface {

    @Override
    public void sendLinkActivation(String email) {

    }

    @Override
    public void sendResetLinkPassword(String email) {

    }
}
