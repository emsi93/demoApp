package com.example.demoapp.mvc.form;

import lombok.Data;

@Data
public class EmailForm {

    private String email;

    public EmailForm() {

    }

    public EmailForm(String email) {
        this.email = email;
    }
}
