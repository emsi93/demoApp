package com.example.demoapp.mvc.form;

import lombok.Data;

@Data
public class PasswordsForm {

    private String password;
    private String password2;

    public PasswordsForm(){

    }

    public PasswordsForm(String password, String password2) {
        this.password = password;
        this.password2 = password2;
    }
}
