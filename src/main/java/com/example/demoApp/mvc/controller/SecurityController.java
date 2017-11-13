package com.example.demoapp.mvc.controller;


import com.example.demoapp.configuration.constants.JspViews;
import com.example.demoapp.utils.ModelAndViewUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/security")
public class SecurityController {

    @RequestMapping(value = {"/", "logIn"})
    public ModelAndViewUtil login(HttpServletRequest request, HttpServletResponse response, Integer messageCodeOrNull) {
        ModelAndViewUtil modelAndView = new ModelAndViewUtil(request, JspViews.LOGIN_VIEW);
        modelAndView.addObject("messageCode", messageCodeOrNull);
        return modelAndView;
    }


    @RequestMapping("logout")
    public ModelAndViewUtil logout(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndViewUtil(request, JspViews.LOGOUT_VIEW);
    }

}
