package com.example.demoapp.mvc.service.impl;

import com.example.demoapp.configuration.Config;
import com.example.demoapp.mvc.controller.ErrorController;
import com.example.demoapp.mvc.controller.SecurityController;
import com.example.demoapp.mvc.entity.Link;
import com.example.demoapp.mvc.entity.User;
import com.example.demoapp.mvc.repository.LinkRepository;
import com.example.demoapp.mvc.repository.UserRepository;
import com.example.demoapp.mvc.service.ActiveUserServiceInterface;
import com.example.demoapp.utils.DateUtil;
import com.example.demoapp.utils.ModelAndViewUtil;
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

    private static final String TYPE_LINK = "ACTIVATION";

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
    public ModelAndViewUtil activeUser(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter(Config.TOKEN_PARAM);
        String email = getEmailFromToken(request, token);
        if (email == null) {
            return errorController.errorLinkActive(request, response);
        } else {
            activeUser(email);
            linkRepository.deleteByEmailAndType(email, TYPE_LINK);
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
            Link link = Optional.ofNullable(linkRepository.findByLinkAndType(url, TYPE_LINK)).orElse(new Link(null, null, null, null));
            if (validateLink(link, countByLink)) {
                return link.getEmail();
            } else
                return null;

        }
        return null;
    }

    private boolean validateLink(Link link, long countByLink) {
        String email = link.getEmail();
        if (email == null)
            return false;
        Timestamp timestamp = link.getData();
        if (!dateUtil.checkValidityUrl(timestamp))
            return false;
        long countByEmailAndType = linkRepository.countByEmailAndType(email, TYPE_LINK);
        boolean isValidLink = true;
        if (countByEmailAndType != 1 || countByLink != 1)
            isValidLink = false;
        return isValidLink;
    }
}
