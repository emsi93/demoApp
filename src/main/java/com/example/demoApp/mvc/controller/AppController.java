package com.example.demoApp.mvc.controller;

import com.example.demoApp.utils.JspViews;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView(JspViews.HOME_VIEW);
        return modelAndView;
    }
}
