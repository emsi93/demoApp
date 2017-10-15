package com.example.demoApp.mvc.service;

import com.example.demoApp.utils.ModelAndViewUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActiveUserServiceInterface {

    public ModelAndViewUtils activeUser(HttpServletRequest request, HttpServletResponse response);
}
