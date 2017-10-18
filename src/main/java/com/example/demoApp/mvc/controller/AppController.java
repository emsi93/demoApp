package com.example.demoApp.mvc.controller;

import com.example.demoApp.configuration.constants.JspViews;
import com.example.demoApp.utils.ModelAndViewUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/home")
    public ModelAndViewUtils home(HttpServletRequest request, HttpServletResponse response) {
        ModelAndViewUtils modelAndView = ModelAndViewUtils.createModelAndViewWithUserName(request,JspViews.HOME_VIEW);
        return modelAndView;
    }
}
