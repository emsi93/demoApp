package com.example.demoApp.mvc.service;

public interface ServiceEmailInterface {

    public void sendLinkActivation(String email);

    public void sendResetLinkPassword(String email);

}
