package com.example.demoApp.mvc.service;

import com.example.demoApp.mvc.entity.User;

import java.security.NoSuchAlgorithmException;


public interface ServiceUserInterface {

    User findUserByEmail(String email);

    void registerUser(User user) throws NoSuchAlgorithmException;
}
