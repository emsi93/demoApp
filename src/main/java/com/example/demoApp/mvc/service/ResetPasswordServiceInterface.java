package com.example.demoApp.mvc.service;

import com.example.demoApp.mvc.form.EmailForm;
import com.example.demoApp.mvc.form.PasswordsForm;
import com.example.demoApp.utils.ModelAndViewUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ResetPasswordServiceInterface {

    public ModelAndViewUtils emailFormGet(HttpServletRequest request,
                                               HttpServletResponse response, EmailForm emailOrNull, Integer message);

    public ModelAndViewUtils emailFormPost(HttpServletRequest request,
                                           HttpServletResponse response, @ModelAttribute("emailForm") @Validated EmailForm email,
                                           BindingResult result);

    public ModelAndViewUtils resetPasswordGet(HttpServletRequest request,
                                              HttpServletResponse response, PasswordsForm passwordsForm, Integer messageCode);

    public ModelAndViewUtils resetPasswordPost(HttpServletRequest request,
                                               HttpServletResponse response, @ModelAttribute("passwordsForm") @Validated PasswordsForm passwordsForm,
                                               BindingResult result) throws NoSuchAlgorithmException, IOException;
}
