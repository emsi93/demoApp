package com.example.demoapp.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@ConfigurationProperties(prefix = "application")
@Data
public class PasswordEncoderUtil implements PasswordEncoder {

    @Value("${password.encoder.algorithm}")
    private String algorithm;

    public String encode(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(algorithm.trim());
        md.update(password.getBytes());

        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    @Override
    public String encode(CharSequence charSequence) {
        try {
            String password = charSequence.toString();
            return encode(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedDbPassword) {
        String plainText = charSequence.toString();
        String encodePassword = "";
        try {
            encodePassword = encode(plainText);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        boolean validPassword = false;
        if (encodePassword.equals(encodedDbPassword))
            validPassword = true;
        return validPassword;
    }
}