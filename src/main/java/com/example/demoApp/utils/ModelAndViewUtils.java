package com.example.demoApp.utils;

import com.example.demoApp.configuration.Config;
import com.example.demoApp.configuration.constants.Languages;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ModelAndViewUtils extends ModelAndView {

    public ModelAndViewUtils(HttpServletRequest request, String jspName) {
        super(jspName);
        addObject("recaptchaUrl", returnRecaptchaUrl(request));
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