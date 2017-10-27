package com.example.demoApp.mvc.validator;

import com.example.demoApp.mvc.form.UserForm;
import com.example.demoApp.mvc.repository.UserRepository;
import com.example.demoApp.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserFormValidator implements Validator {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordValidator passwordValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm user = (UserForm) o;

        ValidationUtil.rejectIfEmpty(errors, "login",
                messageSourceAccessor.getMessage("validator.obligatory.field"));

        ValidationUtil.rejectIfEmpty(errors, "email",
                messageSourceAccessor.getMessage("validator.obligatory.field"));

        ValidationUtil.rejectIfEmpty(errors, "password",
                messageSourceAccessor.getMessage("validator.obligatory.field"));

        ValidationUtil.rejectIfEmpty(errors, "password2",
                messageSourceAccessor.getMessage("validator.obligatory.field"));


        Long countByLogin = userRepository.countByLogin(user.getEmail());
        Long countByEmail = userRepository.countByEmail(user.getEmail());

        if (countByEmail > 0)
            ValidationUtil.reject(errors, "email", messageSourceAccessor.getMessage("validator.email.is.used"));

        if (countByLogin > 0)
            ValidationUtil.reject(errors, "login",  messageSourceAccessor.getMessage("validator.login.is.used"));

        if (!user.getPassword().equals(user.getPassword2()))
            ValidationUtil.reject(errors, "password",  messageSourceAccessor.getMessage("validator.email.is.not.used"));
    }
}
