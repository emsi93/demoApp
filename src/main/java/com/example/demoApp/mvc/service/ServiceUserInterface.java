package com.example.demoApp.mvc.service;

import com.example.demoApp.mvc.entity.User;
import com.example.demoApp.mvc.form.UserForm;

import java.security.NoSuchAlgorithmException;


public interface ServiceUserInterface {

    User findUserByEmail(String email);

    void registerUser(UserForm user) throws NoSuchAlgorithmException;
}
