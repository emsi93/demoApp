package test;

import com.example.demoApp.utils.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Timestamp;

public class DateUtilTest {

    private DateUtil dateUtil;

    @Before
    public void init(){
        dateUtil = new DateUtil();
    }

    @Test
    public void shouldReturnUrlIsValid(){
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        dateUtil.setNumberOfHours("1");
        boolean isValid = dateUtil.checkValidityUrl(timestamp);
        assertEquals(true,isValid);
    }


    @Test
    public void shouldReturnUrlIsNotValid(){
        Timestamp timestamp = Timestamp.valueOf("2011-10-02 18:48:05");

        dateUtil.setNumberOfHours("1");
        boolean isValid = dateUtil.checkValidityUrl(timestamp);
        assertEquals(false,isValid);
    }
}
