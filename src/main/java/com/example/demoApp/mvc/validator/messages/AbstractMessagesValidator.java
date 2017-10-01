package com.example.demoApp.mvc.validator.messages;


public abstract class AbstractMessagesValidator {

    protected String obligatoryFieldErrorMsg;
    protected String emailIsUsed;
    protected String loginIsUsed;
    protected String differentPassword;

    public String getObligatoryFieldErrorMsg() {
        return obligatoryFieldErrorMsg;
    }

    public String getEmailIsUsed() {
        return emailIsUsed;
    }

    public String getLoginIsUsed() {
        return loginIsUsed;
    }

    public String getDifferentPassword() {
        return differentPassword;
    }
}
