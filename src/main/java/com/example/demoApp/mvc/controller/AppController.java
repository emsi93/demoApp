package com.example.demoApp.mvc.controller;

import com.example.demoApp.utils.PasswordEncoderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/main")
public class AppController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @RequestMapping(value={"/","index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
    @RequestMapping("/home")
    public ModelAndView home() throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }
}
