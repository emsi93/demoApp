package com.example.demoApp.mvc.service;

public interface EmailServiceInterface {

    public void sendLinkActivation(String email);

    public void sendResetLinkPassword(String email);

}
