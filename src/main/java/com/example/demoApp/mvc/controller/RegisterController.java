package com.example.demoApp.mvc.controller;


import com.example.demoApp.mvc.form.UserForm;
import com.example.demoApp.mvc.service.RegisterServiceInterface;
import com.example.demoApp.mvc.validator.UserFormValidator;
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
@RequestMapping("/main")
public class RegisterController {

    @Autowired
    private RegisterServiceInterface registerServiceInterface;

    @Autowired
    private UserFormValidator userValidator;

    @InitBinder("userForm")
    protected void initRegisterFormValidator(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public ModelAndViewUtils registerGet(HttpServletRequest request,
                                         HttpServletResponse response, UserForm userOrNull, Integer messageCodeOrNull) {
        return registerServiceInterface.registerGet(request, response, userOrNull, messageCodeOrNull);
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ModelAndViewUtils registerPost(HttpServletRequest request,
                                     HttpServletResponse response, @ModelAttribute("userForm") @Validated UserForm user,
                                     BindingResult result) throws NoSuchAlgorithmException, IOException {
        return registerServiceInterface.registerPost(request, response, user, result);
    }
}
