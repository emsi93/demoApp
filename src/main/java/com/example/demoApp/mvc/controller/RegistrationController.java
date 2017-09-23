package com.example.demoApp.mvc.controller;

import com.example.demoApp.mvc.entity.User;
import com.example.demoApp.mvc.service.ServiceUserInterface;
import com.example.demoApp.mvc.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/main")
public class RegistrationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private final static String SUCCESS_MSG="Rejestracja przebiegła pomyślnie.";
    private final static String SUCCESS_ERROR="Źle wypełniłeś pola.";

    @Autowired
    private ServiceUserInterface serviceUserInterface;

    @Autowired
    private UserValidator userValidator;

    @InitBinder("userForm")
    protected void initRegisterFormValidator(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping(value="signup", method = RequestMethod.GET)
    public ModelAndView errorLoginGet(User userOrNull, Integer messageCodeOrNull){
        ModelAndView modelAndView = new ModelAndView("registration");

        userOrNull = new User(null,null,null,false,null);
        if (messageCodeOrNull != null) {
            switch (messageCodeOrNull) {
                case 1:
                    modelAndView.addObject("messageCode", 1);
                    break;
                case 2:
                    modelAndView.addObject("messageCode", 2);
                    break;
                default:
                    break;
            }
        }
        modelAndView.addObject("userForm", userOrNull);
        return modelAndView;
    }

    @RequestMapping(value="signup", method = RequestMethod.POST)
    public ModelAndView errorLoginPost(@ModelAttribute("userForm") @Validated User user,
                                       BindingResult result) throws NoSuchAlgorithmException {

        if(result.hasErrors())
        {
            return errorLoginGet(user,1);
        }
        else
        {
            serviceUserInterface.registerUser(user);
            return errorLoginGet(user,2);
        }
    }
}
