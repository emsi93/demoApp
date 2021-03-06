package com.example.demoapp.mvc.service;

import com.example.demoapp.mvc.form.UserForm;
import com.example.demoapp.utils.ModelAndViewUtil;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public interface RegisterServiceInterface {

    ModelAndViewUtil registerGet(HttpServletRequest request,
                                 HttpServletResponse response, UserForm userOrNull, Integer messageCodeOrNull);

    ModelAndViewUtil registerPost(HttpServletRequest request,
                                  HttpServletResponse response, UserForm user,
                                  BindingResult result) throws NoSuchAlgorithmException, IOException;
}
