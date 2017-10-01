package com.example.demoApp.mvc.validator.messages;

public class MessagesValidatorPL extends AbstractMessagesValidator{

    public MessagesValidatorPL(){
        obligatoryFieldErrorMsg = "Pole jest wymagane.";
        emailIsUsed = "E-mail jest już użyty.";
        loginIsUsed = "Login jest juz użyty.";
        differentPassword = "Podane hasła muszą być takie same.";
    }
}
