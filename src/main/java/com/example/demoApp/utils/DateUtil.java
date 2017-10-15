package com.example.demoApp.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;

@Component("dateUtl")
@Scope
@Data
public class DateUtil {

    @Value("${hours}")
    private String numberOfHours;

    public boolean checkValidityUrl(Timestamp timestamp1){

        Timestamp timestamp2 = getCurrentTime();
        long milliseconds = timestamp2.getTime() - timestamp1.getTime();
        int seconds = (int) milliseconds / 1000;

        int hours = seconds / 3600;
        if(hours<Integer.parseInt(numberOfHours))
            return true;
        else
            return false;

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
