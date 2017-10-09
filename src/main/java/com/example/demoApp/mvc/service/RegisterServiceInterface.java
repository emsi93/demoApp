package com.example.demoApp.mvc.service;

import com.example.demoApp.mvc.form.UserForm;
import com.example.demoApp.utils.ModelAndViewUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public interface RegisterServiceInterface {

    ModelAndViewUtils registerGet(HttpServletRequest request,
                                  HttpServletResponse response, UserForm userOrNull, Integer messageCodeOrNull);

    ModelAndViewUtils registerPost(HttpServletRequest request,
                              HttpServletResponse response, UserForm user,
                              BindingResult result) throws NoSuchAlgorithmException, IOException;
}
