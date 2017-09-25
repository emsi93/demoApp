package test;

import com.example.demoApp.mvc.validator.PasswordValidator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @Before
    public void init(){
        passwordValidator = new PasswordValidator();
    }

    @Test
    public void tooShortPassword(){
        String password = "test";
        passwordValidator.setMinLength(8);

        boolean response = passwordValidator.checkLengthPassword(password);
        assertEquals(false,response);
    }

    @Test
    public void tooLongPassword(){
        String password = "012345678901234567890";
        passwordValidator.setMaxLength(20);

        boolean response = passwordValidator.checkLengthPassword(password);
        assertEquals(false,response);
    }

    @Test
    public void ifContainLargeLetters(){
        String password = "A12345678";
        passwordValidator.setMinNumberOfLargeLetters(1);

        boolean response = passwordValidator.checkLengthPassword(password);
        assertEquals(false,response);
    }

    @Test
    public void ifContainSpecialCharacters(){
        String password = "123ADs@1234";
        passwordValidator.setMinNumberOfSpecialChars(1);

        boolean response = passwordValidator.checkLengthPassword(password);
        assertEquals(false,response);
    }
}
