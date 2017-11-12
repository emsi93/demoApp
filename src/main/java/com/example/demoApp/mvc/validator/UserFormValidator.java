package com.example.demoapp.mvc.validator;

import com.example.demoapp.mvc.form.UserForm;
import com.example.demoapp.mvc.repository.UserRepository;
import com.example.demoapp.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserFormValidator implements Validator {

    private static final String VALIDATOR_OBLIGATORY_FIELD = "validator.obligatory.field";

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
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "email",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "password",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "password2",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));


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
