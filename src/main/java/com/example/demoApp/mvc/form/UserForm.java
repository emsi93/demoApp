package com.example.demoApp.mvc.form;

import lombok.Data;

@Data
public class UserForm {

    private String login;
    private String email;
    private String password;
    private String password2;

    public UserForm(String login, String email, String password, String password2) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }
}
