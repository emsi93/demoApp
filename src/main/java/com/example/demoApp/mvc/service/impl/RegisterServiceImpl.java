package com.example.demoapp.mvc.service.impl;


import com.example.demoapp.configuration.CaptchaConfig;
import com.example.demoapp.configuration.Config;
import com.example.demoapp.configuration.constants.JspViews;
import com.example.demoapp.mvc.entity.User;
import com.example.demoapp.mvc.form.UserForm;
import com.example.demoapp.mvc.repository.UserRepository;
import com.example.demoapp.mvc.service.EmailServiceInterface;
import com.example.demoapp.mvc.service.RegisterServiceInterface;
import com.example.demoapp.mvc.validator.CaptchaValidator;
import com.example.demoapp.utils.ModelAndViewUtils;
import com.example.demoapp.utils.PasswordEncoderUtil;
import com.example.demoapp.utils.email.EmailActivation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix="application")
public class RegisterServiceImpl implements RegisterServiceInterface {

    @Value("${host}")
    private String host;

    @Autowired
    private CaptchaConfig captchaConfig;

    @Autowired
    private CaptchaValidator captchaValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Autowired
    private EmailServiceInterface emailServiceInterface;

    @Override
    public ModelAndViewUtils registerGet(HttpServletRequest request, HttpServletResponse response, UserForm userOrNull, Integer messageCodeOrNull) {
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request,JspViews.REGISTER_VIEW);
        modelAndView.addObject("messageCode", messageCodeOrNull);
        modelAndView.addObject("recaptchaSiteKey", captchaConfig.getSiteKey());
        modelAndView.addObject("userForm", userOrNull);
        return modelAndView;

    }

    @Override
    public ModelAndViewUtils registerPost(HttpServletRequest request, HttpServletResponse response, UserForm user, BindingResult result) throws NoSuchAlgorithmException, IOException {
        boolean validCaptcha = captchaValidator.verify(request.getParameter(Config.RECAPTCHA_PARAM));
        if (result.hasErrors() || !validCaptcha) {
            return registerGet(request, response, user, 1);
        } else {

            registerUser(user);
            emailServiceInterface.sendEmail(new EmailActivation(user.getEmail(), host));
            return registerGet(request, response, new UserForm(null, null, null, null), 2);
        }
    }

    private void registerUser(UserForm userForm) throws NoSuchAlgorithmException {
        String password = userForm.getPassword();
        password = passwordEncoderUtil.encode(password);
        User user = new User();
        user.setPassword(password);
        user.setActive(false);
        user.setRole("ROLE_ADMIN");
        user.setEmail(userForm.getEmail());
        user.setLogin(userForm.getLogin());
        userRepository.save(user);
    }
}
