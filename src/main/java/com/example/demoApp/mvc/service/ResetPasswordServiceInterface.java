package com.example.demoapp.mvc.service;

import com.example.demoapp.mvc.form.EmailForm;
import com.example.demoapp.mvc.form.PasswordsForm;
import com.example.demoapp.utils.ModelAndViewUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ResetPasswordServiceInterface {

    ModelAndViewUtil emailFormGet(HttpServletRequest request,
                                  HttpServletResponse response, EmailForm emailOrNull, Integer message);

    ModelAndViewUtil emailFormPost(HttpServletRequest request,
                                   HttpServletResponse response, @ModelAttribute("emailForm") @Validated EmailForm email,
                                   BindingResult result);

    ModelAndViewUtil resetPasswordGet(HttpServletRequest request,
                                      HttpServletResponse response, PasswordsForm passwordsForm, Integer messageCode);

    ModelAndViewUtil resetPasswordPost(HttpServletRequest request,
                                       HttpServletResponse response, @ModelAttribute("passwordsForm") @Validated PasswordsForm passwordsForm,
                                       BindingResult result) throws NoSuchAlgorithmException, IOException;
}
