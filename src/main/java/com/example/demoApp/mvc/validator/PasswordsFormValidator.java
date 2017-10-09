package com.example.demoApp.mvc.validator;

import com.example.demoApp.mvc.form.PasswordsForm;
import com.example.demoApp.utils.ValidationUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("passwordsFormValidator")
@Scope("singleton")
public class PasswordsFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordsForm.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        PasswordsForm passwordsForm = (PasswordsForm) o;

        ValidationUtil.rejectIfEmpty(errors,"password","Pole wymagane.");

        ValidationUtil.rejectIfEmpty(errors,"password2","Pole wymagane.");

        if(!passwordsForm.getPassword().equals(passwordsForm.getPassword2()))
            ValidationUtil.reject(errors,"password", "Hasła muszą byc takie same.");
    }
}
