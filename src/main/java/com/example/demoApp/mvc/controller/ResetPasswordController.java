package com.example.demoApp.mvc.controller;


import com.example.demoApp.mvc.form.EmailForm;
import com.example.demoApp.mvc.form.PasswordsForm;
import com.example.demoApp.mvc.service.ResetPasswordServiceInterface;
import com.example.demoApp.mvc.validator.EmailAddressValidator;
import com.example.demoApp.mvc.validator.PasswordsFormValidator;
import com.example.demoApp.utils.ModelAndViewUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@RequestMapping("/password")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordServiceInterface resetPasswordServiceInterface;

    @Autowired
    private PasswordsFormValidator passwordsFormValidator;

    @InitBinder("passwordsForm")
    public void initPasswordsFormValidator(WebDataBinder binder) {
        binder.setValidator(passwordsFormValidator);
    }

    @Autowired
    private EmailAddressValidator emailAddressValidator;

    @InitBinder("emailForm")
    protected void initEmailAddressValidator(WebDataBinder binder) {
        binder.setValidator(emailAddressValidator);
    }

    @RequestMapping(value = "emailForm", method = RequestMethod.GET)
    public ModelAndViewUtils emailFormGet(HttpServletRequest request,
                                          HttpServletResponse response, EmailForm emailOrNull, Integer message) {
        return resetPasswordServiceInterface.emailFormGet(request, response, emailOrNull, message);
    }

    @RequestMapping(value = "emailForm", method = RequestMethod.POST)
    public ModelAndViewUtils emailFormPost(HttpServletRequest request,
                                           HttpServletResponse response, @ModelAttribute("emailForm") @Validated EmailForm email,
                                           BindingResult result) {
        return resetPasswordServiceInterface.emailFormPost(request, response, email, result);
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.GET)
    public ModelAndViewUtils resetPasswordGet(HttpServletRequest request,
                                              HttpServletResponse response, PasswordsForm passwordsForm, Integer messageCode) {
        return resetPasswordServiceInterface.resetPasswordGet(request, response, passwordsForm, messageCode);
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public ModelAndViewUtils resetPasswordPost(HttpServletRequest request,
                                               HttpServletResponse response, @ModelAttribute("passwordsForm") @Validated PasswordsForm passwordsForm,
                                               BindingResult result) throws NoSuchAlgorithmException, IOException {
        return resetPasswordServiceInterface.resetPasswordPost(request, response, passwordsForm, result);
    }
}
