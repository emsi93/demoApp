package com.example.demoApp.mvc.service.impl;

import com.example.demoApp.configuration.CaptchaConfig;
import com.example.demoApp.configuration.Config;
import com.example.demoApp.configuration.constants.JspViews;
import com.example.demoApp.mvc.controller.SecurityController;
import com.example.demoApp.mvc.entity.ResetPasswordLink;
import com.example.demoApp.mvc.entity.User;
import com.example.demoApp.mvc.form.EmailForm;
import com.example.demoApp.mvc.form.PasswordsForm;
import com.example.demoApp.mvc.repository.ResetPasswordLinkRepository;
import com.example.demoApp.mvc.repository.UserRepository;
import com.example.demoApp.mvc.service.ResetPasswordServiceInterface;
import com.example.demoApp.mvc.service.EmailServiceInterface;
import com.example.demoApp.mvc.validator.CaptchaValidator;
import com.example.demoApp.utils.MessageCode;
import com.example.demoApp.utils.ModelAndViewUtils;
import com.example.demoApp.utils.PasswordEncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Slf4j
@Service
@Transactional
class ResetPasswordServiceImpl implements ResetPasswordServiceInterface {

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
    private ResetPasswordLinkRepository resetPasswordLinkRepository;

    @Override
    public ModelAndViewUtils emailFormGet(HttpServletRequest request, HttpServletResponse response, EmailForm emailOrNull, Integer messageOrNull) {
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request, JspViews.EMAIL_FORM);
        modelAndView.addObject("messageCode", MessageCode.getMessageCode(messageOrNull));
        modelAndView.addObject("emailForm", emailOrNull);
        return modelAndView;
    }

    @Override
    public ModelAndViewUtils emailFormPost(HttpServletRequest request, HttpServletResponse response, EmailForm emailform, BindingResult result) {
        if (result.hasErrors()) {
            return emailFormGet(request, response, emailform, 1);
        } else {
            emailServiceInterface.sendResetLinkPassword(emailform.getEmail());
            return emailFormGet(request, response, new EmailForm(null), 2);
        }
    }

    @Override
    public ModelAndViewUtils resetPasswordGet(HttpServletRequest request, HttpServletResponse response, PasswordsForm passwordsForm, Integer messageCode) {
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request, JspViews.RESET_PASSWORD_VIEW);
        String token = request.getParameter(Config.TOKEN_PARAM);
        modelAndView.addObject("errorUrlCode",gerErroUrlCode(request,token));
        modelAndView.addObject("messageCode", MessageCode.getMessageCode(messageCode));
        modelAndView.addObject("recaptchaSiteKey", captchaConfig.getSiteKey());
        modelAndView.addObject("passwordsForm", passwordsForm);
        modelAndView.addObject("url", "/password/resetPassword?token="+token);
        return modelAndView;
    }

    @Override
    public ModelAndViewUtils resetPasswordPost(HttpServletRequest request, HttpServletResponse response, PasswordsForm passwordsForm, BindingResult result) throws NoSuchAlgorithmException, IOException {
        boolean validCaptcha = captchaValidator.verify(request.getParameter(Config.RECAPTCHA_PARAM));
        if (result.hasErrors() || !validCaptcha) {
            return resetPasswordGet(request, response, passwordsForm, 1);
        } else {
            resetPassword(request,passwordsForm);
            return securityController.login(request,response);
        }
    }

    private int gerErroUrlCode(HttpServletRequest request, String token){
        if (token != null) {
            String url = request.getRequestURL().toString() + "?"+Config.TOKEN_PARAM+"="+token;
            long countByLink = resetPasswordLinkRepository.countByLink(url);
            ResetPasswordLink resetPasswordLink = Optional.ofNullable(resetPasswordLinkRepository.findByLink(url)).orElse(new ResetPasswordLink(null,null));
            String email = resetPasswordLink.getEmail();
            if(email == null)
                return 3;
            long countByEmail = resetPasswordLinkRepository.countByEmail(email);
            if (countByEmail != 1 || countByLink != 1)
                return 3;
            else
                return 0;
        }
        return 3;
    }

    private void resetPassword(HttpServletRequest request, PasswordsForm passwordsForm) throws NoSuchAlgorithmException {
        String password = passwordEncoderUtil.encode(passwordsForm.getPassword());
        String token = request.getParameter(Config.TOKEN_PARAM);
        String url = request.getRequestURL().toString() + "?"+Config.TOKEN_PARAM+"="+token;
        ResetPasswordLink resetPasswordLink = resetPasswordLinkRepository.findByLink(url);
        String email = resetPasswordLink.getEmail();
        User user = userRepository.findByEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        resetPasswordLinkRepository.delete(resetPasswordLink);
    }
}