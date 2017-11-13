package com.example.demoapp.utils;

import com.example.demoapp.configuration.Config;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ModelAndViewUtil extends ModelAndView {

    public ModelAndViewUtil(HttpServletRequest request, String jspName) {
        super(jspName);
        addObject("recaptchaUrl", returnRecaptchaUrl(request));
    }

    public static ModelAndViewUtil createModelAndViewWithUserName(HttpServletRequest request, String jspName){
        ModelAndViewUtil modelAndView = new ModelAndViewUtil(request,jspName);
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String userName = auth.getName();
        modelAndView.addObject("username", userName);
        return modelAndView;
    }

    private String returnRecaptchaUrl(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        String lang = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("my-locale-cookie")) {
                    lang = cookie.getValue();
                    break;
                }
            }
        }
        return returnRecaptchaUrl(lang);
    }

    private String returnRecaptchaUrl(String lang) {
        switch (lang) {
            case "pl":
                return Config.RECAPTCHA_URL_PL;
            case "en":
                return Config.RECAPTCHA_URL_EN;
            default:
                return Config.RECAPTCHA_URL_PL;
        }
    }
}