package com.example.demoApp.mvc.controller;


import com.example.demoApp.configuration.constants.JspViews;
import com.example.demoApp.utils.ModelAndViewUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("failureLogin")
    public ModelAndViewUtils failureLogin(HttpServletRequest request, HttpServletResponse response){
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request, JspViews.FAILURE_LOGIN_VIEW);
        return modelAndView;
    }

    @RequestMapping("accessDenied")
    public ModelAndViewUtils accessDenied(HttpServletRequest request, HttpServletResponse response){
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request, JspViews.ACCESS_DENIED_VIEW);
        return modelAndView;
    }

    @RequestMapping("errorLinkActive")
    public ModelAndViewUtils errorLinkActive(HttpServletRequest request, HttpServletResponse response){
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request, JspViews.ERROR_LINK_ACTIVE_VIEW);
        return modelAndView;
    }

    @RequestMapping("errorLinkReset")
    public ModelAndViewUtils errorLinkReset(HttpServletRequest request, HttpServletResponse response){
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request, JspViews.ERROR_LINK_RESET_VIEW);
        return modelAndView;
    }
}
