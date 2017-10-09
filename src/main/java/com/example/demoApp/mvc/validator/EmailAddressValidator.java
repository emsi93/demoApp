package com.example.demoApp.mvc.validator;

import com.example.demoApp.configuration.Config;
import com.example.demoApp.configuration.constants.Languages;
import com.example.demoApp.mvc.form.EmailForm;
import com.example.demoApp.mvc.repository.UserRepository;
import com.example.demoApp.mvc.validator.messages.AbstractMessagesValidator;
import com.example.demoApp.mvc.validator.messages.MessagesValidatorEN;
import com.example.demoApp.mvc.validator.messages.MessagesValidatorPL;
import com.example.demoApp.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Component("changePasswordValidator")
@Scope("singleton")
public class EmailAddressValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return EmailForm.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        EmailForm emailForm = (EmailForm) o;

        AbstractMessagesValidator messages = null;

      //  if(Config.LANG.equals(Languages.POLISH) || Config.LANG.equals(null))
            messages = new MessagesValidatorPL();
     //   else
        //    messages = new MessagesValidatorEN();


        ValidationUtil.rejectIfEmpty(errors, "email",
                messages.getObligatoryFieldErrorMsg());

        long countByEmail = userRepository.countByEmail(emailForm.getEmail());

        boolean isValidEmail = isValidEmailAddress(emailForm.getEmail());

        if(countByEmail == 0 && isValidEmail == false){
            ValidationUtil.reject(errors,"email",messages.getEmailIsNotValid());
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
