package com.example.demoapp.mvc.validator;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix="application")
@Data
public class PasswordValidator {

    // SPECIAL CHARACTERS
    // : , / ; ! @ ? * ^ $ % @ &

    @Value("${password.min.length}")
    private int minLength;

    @Value("${password.max.length}")
    private int maxLength;

    @Value("${password.min.number.of.digits}")
    private int minNumberOfDigits;

    @Value("${password.min.number.of.large.letters}")
    private int minNumberOfLargeLetters;

    @Value("${password.min.number.of.special.chars}")
    private int minNumberOfSpecialChars;


    public boolean checkLengthPassword(String password){
        if(password.length()>=minLength && password.length()<=maxLength)
            return true;
        return false;
    }

    public boolean checkNumberOfDigits(String password){
        int count = 0;
        for (int i = 0, len = password.length(); i < len; i++) {
            if (Character.isDigit(password.charAt(i))) {
                count++;
            }
        }
        if(count>=minNumberOfDigits)
            return true;
        else
            return false;
    }

    public boolean checkNumberOfLargeLetters(String password){
        int count = 0;
        for (int i = 0, len = password.length(); i < len; i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                count++;
            }
        }
        if(count>=minNumberOfLargeLetters)
            return true;
        else
            return false;
    }

    public boolean checkNumberOfSpecialCharacters(String password){
        List specialCharacters = initSpecialCharactersList();
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            for (int j=0; j < specialCharacters.size(); j++) {
                if ( password.charAt(i) == ((Character) specialCharacters.get(j)).charValue()) {
                    count++;
                }
            }
        }
        if(count>=minNumberOfSpecialChars)
            return true;
        else
            return false;
    }
    private List<String> initSpecialCharactersList(){
        List specialCharacters = new ArrayList();
        specialCharacters.add(':');
        specialCharacters.add(',');
        specialCharacters.add('/');
        specialCharacters.add(';');
        specialCharacters.add("!");
        specialCharacters.add("@");
        specialCharacters.add("?");
        specialCharacters.add("*");
        specialCharacters.add("%");
        specialCharacters.add("^");
        specialCharacters.add("$");
        specialCharacters.add("#");
        specialCharacters.add("&");
        return specialCharacters;
    }
}
