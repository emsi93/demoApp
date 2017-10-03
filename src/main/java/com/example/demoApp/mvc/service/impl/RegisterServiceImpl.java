package com.example.demoApp.mvc.service.impl;


import com.example.demoApp.configuration.CaptchaConfig;
import com.example.demoApp.configuration.Config;
import com.example.demoApp.utils.JspViews;
import com.example.demoApp.mvc.entity.User;
import com.example.demoApp.mvc.form.UserForm;
import com.example.demoApp.mvc.repository.UserRepository;
import com.example.demoApp.mvc.service.RegisterServiceInterface;
import com.example.demoApp.mvc.validator.CaptchaValidator;
import com.example.demoApp.utils.Languages;
import com.example.demoApp.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class RegisterServiceImpl implements RegisterServiceInterface {

    private final static String LANG_PARAM = "lang";
    private final static String RECAPTCHA_PARAM = "g-recaptcha-response";

    private final static String RECAPTCHA_URL_PL = "https://www.google.com/recaptcha/api.js?hl=pl";
    private final static String RECAPTCHA_URL_EN = "https://www.google.com/recaptcha/api.js?hl=EN";

    @Autowired
    private CaptchaConfig captchaConfig;

    @Autowired
    private CaptchaValidator captchaValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Override
    public ModelAndView registerGet(HttpServletRequest request, HttpServletResponse response, UserForm userOrNull, Integer messageCodeOrNull) {
        ModelAndView modelAndView = new ModelAndView(JspViews.REGISTER_VIEW);
        modelAndView.addObject("messageCode", getMessageCode(messageCodeOrNull));
        modelAndView.addObject("recaptchaUrl", returnRecaptchaUrl(request));
        modelAndView.addObject("recaptchaSiteKey", captchaConfig.getSiteKey());
        modelAndView.addObject("userForm", userOrNull);
        return modelAndView;

    }

    @Override
    public ModelAndView registerPost(HttpServletRequest request, HttpServletResponse response, UserForm user, BindingResult result) throws NoSuchAlgorithmException, IOException {
        boolean validCaptcha = captchaValidator.verify(request.getParameter(RECAPTCHA_PARAM));
        if (result.hasErrors() || !validCaptcha) {
            return registerGet(request, response, user, 1);
        } else {

            registerUser(user);
            return registerGet(request, response, new UserForm(null, null, null, null), 2);
        }
    }

    private int getMessageCode(Integer messageCodeOrNull) {
        int code=0;
        if (messageCodeOrNull != null) {
            switch (messageCodeOrNull) {
                case 1:
                    code = 1;
                    break;
                case 2:
                    code = 2;
                    break;
                default:
                    break;
            }
        }
        return code;
    }

    private void registerUser(UserForm userForm) throws NoSuchAlgorithmException {
        String password = userForm.getPassword();
        password = passwordEncoderUtil.encode(password);
        User user = new User();
        user.setPassword(password);
        user.setActive(true);
        user.setRole("ROLE_ADMIN");
        user.setEmail(userForm.getEmail());
        user.setLogin(userForm.getLogin());
        userRepository.save(user);
    }

    private String returnRecaptchaUrl(HttpServletRequest request) {
        String language = request.getParameter(LANG_PARAM);
        if (language == null || language.equals(Languages.POLISH)) {
            Config.LANG = Languages.POLISH;
            return RECAPTCHA_URL_PL;
        } else {
            Config.LANG = Languages.ENGLISH;
            return RECAPTCHA_URL_EN;
        }
    }
}
