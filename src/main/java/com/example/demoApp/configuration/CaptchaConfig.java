package com.example.demoapp.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("captcha")
@ConfigurationProperties(prefix="application")
@Data
@Scope("singleton")
public class CaptchaConfig {

    @Value("${recaptcha.url}")
    private String url;

    @Value("${recaptcha.site-key}")
    private String siteKey;

    @Value("${recaptcha.secret-key}")
    private String secretKey;
}
