package com.example.demoapp.mvc.validator;

import com.example.demoapp.mvc.form.EmailForm;
import com.example.demoapp.mvc.repository.UserRepository;
import com.example.demoapp.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Component
public class EmailAddressValidator implements Validator {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return EmailForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmailForm emailForm = (EmailForm) o;

        ValidationUtil.rejectIfEmpty(errors, "email",
                messageSourceAccessor.getMessage("validator.obligatory.field"));

        long countByEmail = userRepository.countByEmail(emailForm.getEmail());

        boolean isValidEmail = isValidEmailAddress(emailForm.getEmail());

        if (countByEmail == 0 || isValidEmail == false) {
            ValidationUtil.reject(errors, "email", messageSourceAccessor.getMessage("validator.email.is.not.valid"));
        }
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
