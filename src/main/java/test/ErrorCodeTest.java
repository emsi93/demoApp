package test;

import com.example.demoApp.utils.ErrorCode;
import static org.junit.Assert.*;
import org.junit.Test;


public class ErrorCodeTest {

    @Test
    public void lengthOfErrorCodeShouldBeSix() {

        String errorCode = ErrorCode.generate();

        assertEquals(6, errorCode.length());
    }

    @Test
    public void toLongOfErrorCode() {

        String errorCode = ErrorCode.generate();
        errorCode = errorCode + "1";

        assertNotEquals(6, errorCode.length());
    }

    @Test
    public void tooShortOfErrorCode() {

        String errorCode = ErrorCode.generate();
        errorCode = errorCode.substring(1);

        assertNotEquals(6, errorCode.length());
    }

}