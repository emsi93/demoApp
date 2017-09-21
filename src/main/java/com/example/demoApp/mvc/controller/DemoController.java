package com.example.demoApp.mvc.controller;


import com.example.demoApp.mvc.entity.Pracownik;
import com.example.demoApp.mvc.service.ServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;


@Controller
public class DemoController {

    @Autowired
    private ServiceInterface service;

    @RequestMapping(value ={"/", "index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        Pracownik pracownik = new Pracownik();
        return modelAndView;
    }


}
