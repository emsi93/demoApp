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
}