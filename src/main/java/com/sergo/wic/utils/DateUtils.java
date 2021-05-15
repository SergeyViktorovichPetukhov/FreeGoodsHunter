package com.sergo.wic.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String convertTimestampToStringDate(String format, Timestamp timestamp) {
        Date date = new Date();
        date.setYear(timestamp.getYear());
        date.setMonth(timestamp.getMonth());
        date.setDate(timestamp.getDate());
        return new SimpleDateFormat(format).format(date);
    }
}
