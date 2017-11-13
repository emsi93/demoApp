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
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("failureLogin")
    public ModelAndViewUtil failureLogin(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndViewUtil(request, JspViews.FAILURE_LOGIN_VIEW);
    }

    @RequestMapping("accessDenied")
    public ModelAndViewUtil accessDenied(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndViewUtil(request, JspViews.ACCESS_DENIED_VIEW);
    }

    @RequestMapping("errorLinkActive")
    public ModelAndViewUtil errorLinkActive(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndViewUtil(request, JspViews.ERROR_LINK_ACTIVE_VIEW);
    }

    @RequestMapping("errorLinkReset")
    public ModelAndViewUtil errorLinkReset(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndViewUtil(request, JspViews.ERROR_LINK_RESET_VIEW);
    }
}
