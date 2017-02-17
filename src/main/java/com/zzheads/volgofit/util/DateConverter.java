package com.zzheads.volgofit.util;//

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//  created by zzheads on 14.02.17
//
public class DateConverter {

    private static DateFormat dateFormat(boolean withTime) {
        String datePattern = "dd/MM/yyyy";
        String dateWithTimePattern = "dd/MM/yyyy HH:mm";

        if (withTime)
            return new SimpleDateFormat(dateWithTimePattern);
        else
            return new SimpleDateFormat(datePattern);
    }

    public static String dateToString(Date date, boolean withTime) {
        if (date != null) {
            DateFormat df = dateFormat(withTime);
            return df.format(date);
        }
        return null;
    }

    public static Date stringToDate(String string, boolean withTime) {
        if (string != null) {
            DateFormat df = dateFormat(withTime);
            try {
                return df.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getWordAfterLastPoint(String text) {
        if (text == null) return null;
        String[] words = text.split("\\.");
        String lastWord = null;
        for (String word : words) {
            lastWord = word;
        }
        return lastWord;
    }
}
