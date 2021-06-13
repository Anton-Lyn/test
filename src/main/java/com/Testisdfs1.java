package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Testisdfs1 {
    public static void main(String[] args) throws ParseException {

        LocalTime now = LocalTime.now();
        LocalTime timetest = now.plusMinutes(15);
        LocalTime mytime = LocalTime.of(19, 7);
        if (mytime.isAfter(timetest)) {
            System.out.println("после 15 мин");
        } else {
            System.out.println("до 15 мин");
        }


    }
}
