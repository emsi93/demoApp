package com.example.demoApp.mvc.validator;

import com.example.demoApp.mvc.form.UserForm;
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
public class UserFormValidator implements Validator {
    private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
    private static final String EMAIL_IS_USED = "E-mail jest już użyty.";
    private static final String LOGIN_IS_USED = "Login jest juz użyty";
    private static final String DIFFERENT_PASSWORD = "Podane hasła muszą być takie same.";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordValidator passwordValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {

        UserForm user = (UserForm) o;

        ValidationUtils.rejectIfEmpty(errors, "login",
                OBLIGATORY_FIELD_ERROR_MSG);

        ValidationUtils.rejectIfEmpty(errors, "email",
                OBLIGATORY_FIELD_ERROR_MSG);

        ValidationUtils.rejectIfEmpty(errors, "password",
                OBLIGATORY_FIELD_ERROR_MSG);

        Long countByLogin = userRepository.countByLogin(((UserForm) o).getLogin());
        Long countByEmail = userRepository.countByEmail(((UserForm) o).getEmail());

        if(countByEmail>0)
            ValidationUtils.reject(errors, "email", EMAIL_IS_USED);

        if(countByLogin>0)
            ValidationUtils.reject(errors, "login", LOGIN_IS_USED);

        if(!((UserForm) o).getPassword().equals(((UserForm) o).getPassword2()))
            ValidationUtils.reject(errors, "password", DIFFERENT_PASSWORD);
    }
}
