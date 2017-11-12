package com.example.demoapp.mvc.controller;

import com.example.demoapp.mvc.service.ActiveUserServiceInterface;
import com.example.demoapp.utils.ModelAndViewUtils;
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
