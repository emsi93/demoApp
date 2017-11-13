package com.example.demoapp.mvc.controller;

import com.example.demoapp.configuration.constants.JspViews;
import com.example.demoapp.utils.ModelAndViewUtil;
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
    public ModelAndViewUtil index(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndViewUtil(request, JspViews.INDEX_VIEW);
    }
}
