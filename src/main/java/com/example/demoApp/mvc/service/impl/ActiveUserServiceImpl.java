package com.example.demoApp.mvc.service.impl;

import com.example.demoApp.configuration.Config;
import com.example.demoApp.configuration.constants.JspViews;
import com.example.demoApp.mvc.controller.ErrorController;
import com.example.demoApp.mvc.controller.SecurityController;
import com.example.demoApp.mvc.entity.Link;
import com.example.demoApp.mvc.entity.User;
import com.example.demoApp.mvc.repository.LinkRepository;
import com.example.demoApp.mvc.repository.UserRepository;
import com.example.demoApp.mvc.service.ActiveUserServiceInterface;
import com.example.demoApp.utils.DateUtil;
import com.example.demoApp.utils.ModelAndViewUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ActiveUserServiceImpl implements ActiveUserServiceInterface {

    @Autowired
    private SecurityController securityController;

    @Autowired
    private ErrorController errorController;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DateUtil dateUtil;

    @Override
    public ModelAndViewUtils activeUser(HttpServletRequest request, HttpServletResponse response) {
        ModelAndViewUtils modelAndView = new ModelAndViewUtils(request, JspViews.ERROR_LINK_ACTIVE_VIEW);
        String token = request.getParameter(Config.TOKEN_PARAM);
        String email = getEmailFromToken(request, token);
        if (email == null) {
            return errorController.errorLinkActive(request, response);
        } else {
            activeUser(email);
            linkRepository.deleteByEmailAndType(email,"ACTIVATION");
            return securityController.login(request, response, 1);
        }

    }

    private void activeUser(String email) {
        User user = userRepository.findByEmail(email);
        user.setActive(true);
        userRepository.save(user);
    }

    private String getEmailFromToken(HttpServletRequest request, String token) {
        if (token != null) {
            String url = request.getRequestURL().toString() + "?" + Config.TOKEN_PARAM + "=" + token;
            long countByLink = linkRepository.countByLink(url);
            Link link = Optional.ofNullable(linkRepository.findByLinkAndType(url, "ACTIVATION")).orElse(new Link(null, null, null, null));
            if(validateLink(link,countByLink)){
                return link.getEmail();
            } else
                return null;

        }
        return null;
    }

    private boolean validateLink(Link link, long countByLink){
        String email = link.getEmail();
        if (email == null)
            return false;
        Timestamp timestamp = link.getData();
        if (!dateUtil.checkValidityUrl(timestamp))
            return false;
        long countByEmailAndType = linkRepository.countByEmailAndType(email, "ACTIVATION");
        if (countByEmailAndType != 1 || countByLink != 1)
            return false;
        else {
            return true;
        }
    }
}
