package com.example.demoApp.mvc.validator;

import com.example.demoApp.mvc.form.UserForm;
import com.example.demoApp.mvc.repository.UserRepository;
import com.example.demoApp.utils.ValidationUtil;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component("userValidator")
@Scope("singleton")
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
    public void validate(@Nullable Object o, Errors errors) {
        UserForm user = (UserForm) o;

        ValidationUtil.rejectIfEmpty(errors, "login",
                messageSourceAccessor.getMessage("validator.obligatory.field"));

        ValidationUtil.rejectIfEmpty(errors, "email",
                messageSourceAccessor.getMessage("validator.obligatory.field"));

        ValidationUtil.rejectIfEmpty(errors, "password",
                messageSourceAccessor.getMessage("validator.obligatory.field"));

        ValidationUtil.rejectIfEmpty(errors, "password2",
                messageSourceAccessor.getMessage("validator.obligatory.field"));

        Long countByLogin = userRepository.countByLogin(((UserForm) o).getLogin());
        Long countByEmail = userRepository.countByEmail(((UserForm) o).getEmail());

        if (countByEmail > 0)
            ValidationUtil.reject(errors, "email", messageSourceAccessor.getMessage("validator.email.is.used"));

        if (countByLogin > 0)
            ValidationUtil.reject(errors, "login",  messageSourceAccessor.getMessage("validator.login.is.used"));

        if (!((UserForm) o).getPassword().equals(((UserForm) o).getPassword2()))
            ValidationUtil.reject(errors, "password",  messageSourceAccessor.getMessage("validator.email.is.not.used"));
    }
}
