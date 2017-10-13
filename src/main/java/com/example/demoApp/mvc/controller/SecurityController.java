package com.example.demoApp.mvc.controller;



import com.example.demoApp.configuration.constants.JspViews;
import com.example.demoApp.utils.ModelAndViewUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/security")
public class SecurityController {

    @RequestMapping(value ={"/", "logIn"})
    public ModelAndViewUtils login(HttpServletRequest request, HttpServletResponse response, Integer messageCodeOrNull) {
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request,JspViews.LOGIN_VIEW);
        modelAndView.addObject("messageCode", messageCodeOrNull);
        return modelAndView;
    }


    @RequestMapping("logout")
    public ModelAndViewUtils logout(HttpServletRequest request, HttpServletResponse response){
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request, JspViews.LOGOUT_VIEW);
        return modelAndView;
    }

}
