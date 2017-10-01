package com.example.demoApp.mvc.validator;

import com.example.demoApp.configuration.Config;
import com.example.demoApp.mvc.form.UserForm;
import com.example.demoApp.mvc.repository.UserRepository;
import com.example.demoApp.mvc.validator.messages.AbstractMessagesValidator;
import com.example.demoApp.mvc.validator.messages.MessagesValidatorEN;
import com.example.demoApp.mvc.validator.messages.MessagesValidatorPL;
import com.example.demoApp.utils.ValidationUtil;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component("userValidator")
@Scope("singleton")
public class UserFormValidator implements Validator {


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

        AbstractMessagesValidator messages = null;

        if(Config.LANG.equals("pl"))
            messages = new MessagesValidatorPL();
        else
            messages = new MessagesValidatorEN();

        ValidationUtil.rejectIfEmpty(errors, "login",
                messages.getObligatoryFieldErrorMsg());

        ValidationUtil.rejectIfEmpty(errors, "email",
                messages.getObligatoryFieldErrorMsg());

        ValidationUtil.rejectIfEmpty(errors, "password",
                messages.getObligatoryFieldErrorMsg());

        ValidationUtil.rejectIfEmpty(errors, "password2",
                messages.getObligatoryFieldErrorMsg());

        Long countByLogin = userRepository.countByLogin(((UserForm) o).getLogin());
        Long countByEmail = userRepository.countByEmail(((UserForm) o).getEmail());

        if (countByEmail > 0)
            ValidationUtil.reject(errors, "email", messages.getEmailIsUsed());

        if (countByLogin > 0)
            ValidationUtil.reject(errors, "login", messages.getLoginIsUsed());

        if (!((UserForm) o).getPassword().equals(((UserForm) o).getPassword2()))
            ValidationUtil.reject(errors, "password", messages.getDifferentPassword());
    }
}
