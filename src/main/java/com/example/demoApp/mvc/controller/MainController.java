package com.example.demoApp.mvc.controller;

import com.example.demoApp.configuration.constants.JspViews;
import com.example.demoApp.utils.ModelAndViewUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/main")
@Slf4j
public class MainController {



    @RequestMapping(value={"/","index"})
    public ModelAndViewUtils index(HttpServletRequest request, HttpServletResponse response){
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request, JspViews.INDEX_VIEW);
        return modelAndView;
    }
}
