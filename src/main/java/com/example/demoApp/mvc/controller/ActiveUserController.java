package com.example.demoApp.mvc.controller;

import com.example.demoApp.configuration.constants.JspViews;
import com.example.demoApp.mvc.service.ActiveUserServiceInterface;
import com.example.demoApp.utils.ModelAndViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/activeUser")
public class ActiveUserController {

    @Autowired
    private ActiveUserServiceInterface activeUserServiceInterface;

    @RequestMapping("/active")
    public ModelAndViewUtils activeUser(HttpServletRequest request, HttpServletResponse response){
        return activeUserServiceInterface.activeUser(request, response);
    }
}
