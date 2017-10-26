package com.example.demoApp.mvc.validator;

import com.example.demoApp.mvc.form.PasswordsForm;
import com.example.demoApp.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("passwordsFormValidator")
@RequiredArgsConstructor
public class PasswordsFormValidator implements Validator {

    private final MessageSourceAccessor messageSourceAccessor;

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
