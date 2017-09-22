package com.example.demoApp.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@ConfigurationProperties(prefix="application")
@Data
public class PasswordEncoder {

    @Value("${password.encoder.algorithm}")
    private String algorithm;

    public String encode(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(password.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}