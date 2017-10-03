package com.example.demoApp.mvc.controller;



import com.example.demoApp.utils.JspViews;
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
        ModelAndView modelAndView = new ModelAndView(JspViews.LOGIN_VIEW);
        modelAndView.addObject("recaptchaUrl",null);
        return modelAndView;
    }


    @RequestMapping("logout")
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView(JspViews.LOGOUT_VIEW);
        return modelAndView;
    }

}
