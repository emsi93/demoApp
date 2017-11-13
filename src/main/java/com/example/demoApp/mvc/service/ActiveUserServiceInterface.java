package com.example.demoapp.mvc.service;

import com.example.demoapp.utils.ModelAndViewUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActiveUserServiceInterface {

    ModelAndViewUtil activeUser(HttpServletRequest request, HttpServletResponse response);
}
