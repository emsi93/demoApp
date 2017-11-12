package com.example.demoapp.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;

@Component
@Data
public class DateUtil {

    @Value("${hours}")
    private String numberOfHours;

    public boolean checkValidityUrl(Timestamp timestamp1){

        Timestamp timestamp2 = getCurrentTime();
        long secs = (timestamp2.getTime() -timestamp1.getTime()) / 1000;
        long hours = secs / 3600;
        boolean urlNotValid = false;
        if(hours<Long.parseLong(numberOfHours))
            urlNotValid = true;

        return urlNotValid;

    }


    private static Timestamp getCurrentTime(){
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.SECOND, 98765);
        return timestamp;
    }
}
