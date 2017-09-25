package test;

import com.example.demoApp.utils.PasswordEncoderUtil;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class PasswordEncoderUtilTest {


    private PasswordEncoderUtil passwordEncoderUtil;

    @Before
    public void init(){
        passwordEncoderUtil = new PasswordEncoderUtil();
    }

    @Test
    public void shouldReturnEncodedPasswordForSHA256(){

        passwordEncoderUtil.setAlgorithm("SHA-256");
        String password = "test";
        String encodedPassword = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";
        String encodedPassword2 = null;
        try {
            encodedPassword2 = passwordEncoderUtil.encode(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assertEquals(encodedPassword,encodedPassword2);
    }

    @Test
    public void shouldReturnEncodedPasswordForMD5(){

        passwordEncoderUtil.setAlgorithm("MD5");
        String password = "test";
        String encodedPassword = "098f6bcd4621d373cade4e832627b4f6";
        String encodedPassword2 = null;
        try {
            encodedPassword2 = passwordEncoderUtil.encode(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assertEquals(encodedPassword,encodedPassword2);
    }

    @Test
    public void shouldReturnEncodedPasswordForSHA1(){

        passwordEncoderUtil.setAlgorithm("SHA-1");
        String password = "test";
        String encodedPassword = "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3";
        String encodedPassword2 = null;
        try {
            encodedPassword2 = passwordEncoderUtil.encode(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assertEquals(encodedPassword,encodedPassword2);
    }
}
