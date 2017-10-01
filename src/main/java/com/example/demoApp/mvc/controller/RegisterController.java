package com.example.demoApp.mvc.controller;

import com.example.demoApp.configuration.CaptchaConfig;
import com.example.demoApp.configuration.Config;
import com.example.demoApp.mvc.form.UserForm;
import com.example.demoApp.mvc.service.ServiceUserInterface;
import com.example.demoApp.mvc.validator.CaptchaValidator;
import com.example.demoApp.mvc.validator.UserFormValidator;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@RequestMapping("/main")
public class RegisterController {

    private final static String LANG_PARAM = "lang";

    private final static String RECAPTCHA_PARAM = "g-recaptcha-response";
    private final static String RECAPTCHA_URL_PL = "https://www.google.com/recaptcha/api.js?hl=pl";
    private final static String RECAPTCHA_URL_EN = "https://www.google.com/recaptcha/api.js?hl=EN";


    @Autowired
    private CaptchaConfig captchaConfig;

    @Autowired
    private CaptchaValidator captchaValidator;

    @Autowired
    private ServiceUserInterface serviceUserInterface;

    @Autowired
    private UserFormValidator userValidator;

    @InitBinder("userForm")
    protected void initRegisterFormValidator(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public ModelAndView registerGet(HttpServletRequest request,
                                    HttpServletResponse response, UserForm userOrNull, Integer messageCodeOrNull) {
        log.info("WESZLO");
        ModelAndView modelAndView = new ModelAndView("registration");
        if (messageCodeOrNull != null) {
            switch (messageCodeOrNull) {
                case 1:
                    modelAndView.addObject("messageCode", 1);
                    break;
                case 2:
                    modelAndView.addObject("messageCode", 2);
                    userOrNull = new UserForm(null, null, null, null);
                    break;
                default:
                    break;
            }
        }
        modelAndView.addObject("recaptchaUrl", returnRecaptchaUrl(request));
        modelAndView.addObject("recaptchaSiteKey", captchaConfig.getSiteKey());
        modelAndView.addObject("userForm", userOrNull);
        return modelAndView;
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ModelAndView registerPost(HttpServletRequest request,
                                     HttpServletResponse response, @ModelAttribute("userForm") @Validated UserForm user,
                                     BindingResult result) throws NoSuchAlgorithmException, IOException {
        boolean validCaptcha = captchaValidator.verify(request.getParameter(RECAPTCHA_PARAM));
        if (result.hasErrors() || !validCaptcha) {
            return registerGet(request, response, user, 1);
        } else {

            serviceUserInterface.registerUser(user);
            return registerGet(request, response, user, 2);
        }
    }

    private String returnRecaptchaUrl(HttpServletRequest request){
        String language = request.getParameter(LANG_PARAM);
        if(language==null || language.equals("pl")){
            Config.LANG = "pl";
            return RECAPTCHA_URL_PL;
        }else{
            Config.LANG = "en";
            return RECAPTCHA_URL_EN;
        }
    }
}
