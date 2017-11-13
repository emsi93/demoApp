package com.example.demoapp.mvc.service.impl;

import com.example.demoapp.configuration.CaptchaConfig;
import com.example.demoapp.configuration.Config;
import com.example.demoapp.configuration.constants.JspViews;
import com.example.demoapp.mvc.controller.ErrorController;
import com.example.demoapp.mvc.controller.SecurityController;
import com.example.demoapp.mvc.entity.Link;
import com.example.demoapp.mvc.entity.User;
import com.example.demoapp.mvc.form.EmailForm;
import com.example.demoapp.mvc.form.PasswordsForm;
import com.example.demoapp.mvc.repository.LinkRepository;
import com.example.demoapp.mvc.repository.UserRepository;
import com.example.demoapp.mvc.service.EmailServiceInterface;
import com.example.demoapp.mvc.service.ResetPasswordServiceInterface;
import com.example.demoapp.mvc.validator.CaptchaValidator;
import com.example.demoapp.utils.DateUtil;
import com.example.demoapp.utils.ModelAndViewUtil;
import com.example.demoapp.utils.PasswordEncoderUtil;
import com.example.demoapp.email.EmailResetPassword;
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
import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix="application")
public class ResetPasswordServiceImpl implements ResetPasswordServiceInterface {

    private static final String TYPE_LINK = "RESET";

    @Value("${host}")
    private String host;

    @Autowired
    private EmailServiceInterface emailServiceInterface;

    @Autowired
    private CaptchaConfig captchaConfig;

    @Autowired
    private CaptchaValidator captchaValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Autowired
    private SecurityController securityController;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private ErrorController errorController;

    @Autowired
    private DateUtil dateUtil;

    @Override
    public ModelAndViewUtil emailFormGet(HttpServletRequest request, HttpServletResponse response, EmailForm emailOrNull, Integer messageCodeOrNull) {
        ModelAndViewUtil modelAndView = new ModelAndViewUtil(request, JspViews.EMAIL_FORM);
        modelAndView.addObject("messageCode", messageCodeOrNull);
        modelAndView.addObject("emailForm", emailOrNull);
        return modelAndView;
    }

    @Override
    public ModelAndViewUtil emailFormPost(HttpServletRequest request, HttpServletResponse response, EmailForm emailform, BindingResult result) {
        if (result.hasErrors()) {
            return emailFormGet(request, response, emailform, 1);
        } else {
            emailServiceInterface.sendEmail(new EmailResetPassword(emailform.getEmail(), host));
            return emailFormGet(request, response, new EmailForm(null), 2);
        }
    }

    @Override
    public ModelAndViewUtil resetPasswordGet(HttpServletRequest request, HttpServletResponse response, PasswordsForm passwordsForm, Integer messageCodeOrNull) {
        ModelAndViewUtil modelAndView = new ModelAndViewUtil(request, JspViews.RESET_PASSWORD_VIEW);
        String token = request.getParameter(Config.TOKEN_PARAM);
        if(gerErrorUrlCode(request, token) == 3)
            return errorController.errorLinkReset(request,response);
        modelAndView.addObject("messageCode", messageCodeOrNull);
        modelAndView.addObject("recaptchaSiteKey", captchaConfig.getSiteKey());
        modelAndView.addObject("passwordsForm", passwordsForm);
        modelAndView.addObject("url", "/password/resetPassword?token=" + token);
        return modelAndView;
    }

    @Override
    public ModelAndViewUtil resetPasswordPost(HttpServletRequest request, HttpServletResponse response, PasswordsForm passwordsForm, BindingResult result) throws NoSuchAlgorithmException, IOException {
        boolean validCaptcha = captchaValidator.verify(request.getParameter(Config.RECAPTCHA_PARAM));
        if (result.hasErrors() || !validCaptcha) {
            return resetPasswordGet(request, response, passwordsForm, 1);
        } else {
            resetPassword(request, passwordsForm);
            return securityController.login(request, response,2);
        }
    }

    private int gerErrorUrlCode(HttpServletRequest request, String token) {
        if (token != null) {
            String url = request.getRequestURL().toString() + "?" + Config.TOKEN_PARAM + "=" + token;
            long countByLink = linkRepository.countByLink(url);
            Link link = Optional.ofNullable(linkRepository.findByLinkAndType(url, TYPE_LINK)).orElse(new Link(null, null, null, null));
            String email = link.getEmail();
            if (email == null)
                return 3;
            Timestamp timestamp = link.getData();
            if(!dateUtil.checkValidityUrl(timestamp))
                return 3;
            long countByEmailAndType = linkRepository.countByEmailAndType(email, TYPE_LINK);
            if (countByEmailAndType != 1 || countByLink != 1)
                return 3;
            else
                return 0;
        }
        return 3;
    }

    private void resetPassword(HttpServletRequest request, PasswordsForm passwordsForm) throws NoSuchAlgorithmException {
        String password = passwordEncoderUtil.encode(passwordsForm.getPassword());
        String token = request.getParameter(Config.TOKEN_PARAM);
        String url = request.getRequestURL().toString() + "?" + Config.TOKEN_PARAM + "=" + token;
        Link link = linkRepository.findByLink(url);
        String email = link.getEmail();
        User user = userRepository.findByEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        linkRepository.delete(link);
    }
}