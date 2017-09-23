package com.example.demoApp.mvc.validator;

import com.example.demoApp.mvc.entity.User;
import com.example.demoApp.mvc.repository.UserRepository;
import com.example.demoApp.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



@Component("userValidator")
@Scope("singleton")
public class UserValidator implements Validator {
    private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
    private static final String EMAIL_IS_USED = "E-mail jest już użyty.";
    private static final String LOGIN_IS_USED = "Login jest juz użyty";

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {

        User user = (User) o;

        ValidationUtils.rejectIfEmpty(errors, "login",
                OBLIGATORY_FIELD_ERROR_MSG);

        ValidationUtils.rejectIfEmpty(errors, "email",
                OBLIGATORY_FIELD_ERROR_MSG);

        ValidationUtils.rejectIfEmpty(errors, "password",
                OBLIGATORY_FIELD_ERROR_MSG);

        Long countByLogin = userRepository.countByLogin(((User) o).getLogin());
        Long countByEmail = userRepository.countByEmail(((User) o).getEmail());

        if(countByEmail>0)
            ValidationUtils.reject(errors, "email", EMAIL_IS_USED);

        if(countByLogin>0)
            ValidationUtils.reject(errors, "login", LOGIN_IS_USED);
    }
}
