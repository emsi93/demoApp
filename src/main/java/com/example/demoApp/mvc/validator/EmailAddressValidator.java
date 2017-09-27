package com.example.demoApp.mvc.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Component("emailAddressValidator")
@Scope("singleton")
public class EmailAddressValidator {

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
