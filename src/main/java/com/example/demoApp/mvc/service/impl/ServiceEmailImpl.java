package com.example.demoApp.mvc.service.impl;

import com.example.demoApp.mvc.service.ServiceEmailInterface;
import org.springframework.stereotype.Service;

@Service
public class ServiceEmailImpl implements ServiceEmailInterface {

    @Override
    public void sendLinkActivation(String email) {

    }

    @Override
    public void sendResetLinkPassword(String email) {

    }
}
