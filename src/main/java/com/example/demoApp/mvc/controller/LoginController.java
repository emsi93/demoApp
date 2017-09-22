package com.example.demoApp.mvc.controller;


import com.example.demoApp.mvc.entity.Pracownik;
import com.example.demoApp.mvc.service.ServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;


@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private ServiceInterface service;

    @RequestMapping(value ={"/", "index"})
    public ModelAndView index() throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }


}
