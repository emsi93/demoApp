package com.example.demoapp.mvc.service;

import com.example.demoapp.utils.ModelAndViewUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActiveUserServiceInterface {

    ModelAndViewUtils activeUser(HttpServletRequest request, HttpServletResponse response);
}
