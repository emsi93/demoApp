package com.example.demoApp.mvc.service.impl;

import com.example.demoApp.mvc.entity.User;
import com.example.demoApp.mvc.repository.UserRepository;
import com.example.demoApp.mvc.service.ServiceUserInterface;
import com.example.demoApp.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
@Service
public class ServiceUserImpl implements ServiceUserInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void registerUser(User user) throws NoSuchAlgorithmException {
        String password = user.getPassword();
        password = passwordEncoderUtil.encode(password);
        user.setPassword(password);
        user.setActive(true);
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);
    }
}
