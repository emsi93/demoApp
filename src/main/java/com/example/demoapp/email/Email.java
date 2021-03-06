package com.example.demoapp.email;

import lombok.Data;

@Data
public abstract class Email {

    protected String recipient;
    protected String url;
    protected String topic;
    protected String content;
    protected String type;
}
