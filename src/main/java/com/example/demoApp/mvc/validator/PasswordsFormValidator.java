package com.example.demoapp.mvc.validator;

import com.example.demoapp.mvc.form.PasswordsForm;
import com.example.demoapp.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordsFormValidator implements Validator {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordsForm.class.equals(aClass);
    }

    @Override
    public void validate( Object o, Errors errors) {
        PasswordsForm passwordsForm = (PasswordsForm) o;

        ValidationUtil.rejectIfEmpty(errors,"password",messageSourceAccessor.getMessage("validator.obligatory.field"));

        ValidationUtil.rejectIfEmpty(errors,"password2",messageSourceAccessor.getMessage("validator.obligatory.field"));

        if(!passwordsForm.getPassword().equals(passwordsForm.getPassword2()))
            ValidationUtil.reject(errors,"password", messageSourceAccessor.getMessage("validator.different.passwords"));
    }
}
