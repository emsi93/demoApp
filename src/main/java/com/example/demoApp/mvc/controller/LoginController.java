package com.example.demoApp.mvc.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/login")
public class LoginController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value ={"/", "logIn"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }


}
