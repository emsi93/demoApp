package com.example.demoApp.mvc.validator.messages;

public class MessagesValidatorEN extends AbstractMessagesValidator{

    public MessagesValidatorEN() {
        obligatoryFieldErrorMsg = "Field is required.";
        emailIsUsed = "Email is already used.";
        loginIsUsed = "Login is already used.";
        differentPassword = "Passwords must be the same.";
    }
}
