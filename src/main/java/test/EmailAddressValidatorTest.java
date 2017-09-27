package test;

import com.example.demoApp.mvc.validator.EmailAddressValidator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EmailAddressValidatorTest {

    private EmailAddressValidator emailAddressValidator;

    @Before
    public void init(){
        emailAddressValidator = new EmailAddressValidator();
    }

    @Test
    public void shouldCheckWhetherEmailAddressIsValid(){
        String emailAddress = "test@gmail.com";

        boolean isValid = emailAddressValidator.isValidEmailAddress(emailAddress);

        assertEquals(true,isValid);
    }

    @Test
    public void shouldCheckWhetherEmailAddressIsNotValid(){
        String emailAddress = "test.com";

        boolean isValid = emailAddressValidator.isValidEmailAddress(emailAddress);

        assertNotEquals(true,isValid);
    }
}