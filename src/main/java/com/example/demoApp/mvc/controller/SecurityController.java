package com.example.demoApp.mvc.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/security")
public class SecurityController {

    @RequestMapping(value ={"/", "logIn"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("recaptchaUrl",null);
        return modelAndView;
    }


    @RequestMapping("logout")
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView("logout");
        return modelAndView;
    }

}
