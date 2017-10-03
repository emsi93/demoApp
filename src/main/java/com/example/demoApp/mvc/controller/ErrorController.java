package com.example.demoApp.mvc.controller;


import com.example.demoApp.utils.JspViews;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("failureLogin")
    public ModelAndView failureLogin(){
        ModelAndView modelAndView = new ModelAndView(JspViews.FAILURE_LOGIN_VIEW);
        return modelAndView;
    }

    @RequestMapping("accessDenied")
    public ModelAndView accessDenied(){
        ModelAndView modelAndView = new ModelAndView(JspViews.ACCESS_DENIED_VIEW);
        return modelAndView;
    }
}
