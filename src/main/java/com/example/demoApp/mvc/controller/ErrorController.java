package com.example.demoApp.mvc.controller;


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
        ModelAndView modelAndView = new ModelAndView("failureLogin");
        return modelAndView;
    }

    @RequestMapping("accessDenied")
    public ModelAndView accessDenied(){
        ModelAndView modelAndView = new ModelAndView("accessDenied");
        return modelAndView;
    }
}
