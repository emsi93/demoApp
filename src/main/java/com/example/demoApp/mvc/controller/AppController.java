package com.example.demoapp.mvc.controller;

import com.example.demoapp.configuration.constants.JspViews;
import com.example.demoapp.utils.ModelAndViewUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/home")
    public ModelAndViewUtil home(HttpServletRequest request, HttpServletResponse response) {
       return  ModelAndViewUtil.createModelAndViewWithUserName(request,JspViews.HOME_VIEW);
    }
}
