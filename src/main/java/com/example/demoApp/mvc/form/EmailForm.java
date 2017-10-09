package com.example.demoApp.mvc.form;

import lombok.Data;

@Data
public class EmailForm {

    private String email;

    public EmailForm(String email) {
        this.email = email;
    }
}
