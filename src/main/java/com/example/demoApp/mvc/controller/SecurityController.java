package com.example.demoApp.mvc.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/security")
public class SecurityController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);


    @RequestMapping(value ={"/", "logIn"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }


    @RequestMapping("logout")
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView("logout");
        return modelAndView;
    }

}
