package com.example.demoApp.mvc.controller;

import com.example.demoApp.utils.JspViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value={"/","index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView(JspViews.INDEX_VIEW);
        return modelAndView;
    }
}
