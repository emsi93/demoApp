package com.example.demoApp.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);


    @RequestMapping("failureLogin")
    public ModelAndView failureLogin(){
        ModelAndView modelAndView = new ModelAndView("failureLogin");
        return modelAndView;
    }

    @RequestMapping("accessDenied")
    public ModelAndView accessDenied(){
        ModelAndView modelAndView = new ModelAndView("accessDenied");
        return modelAndView;
    }
}
