package com.starbucks;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.starbucks.constant.Constants.EMAIL_VALIDATION_REGEX;
import static com.starbucks.constant.Constants.SIMPLE_DATE_FORMAT;
import static com.starbucks.constant.Constants.SIMPLE_DATE_VALIDATION_REGEX;

public class Utils {
    public static boolean isValidEmailAddress(final String email) {
        String regex = EMAIL_VALIDATION_REGEX;
        return email.matches(regex);
    }

    public static boolean isValidDate(final String text) {
        if (text == null || !text.matches(SIMPLE_DATE_VALIDATION_REGEX)) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        df.setLenient(false);
        try {
            df.parse(text);
            return true;
        } catch (final ParseException ex) {
            return false;
        }
    }
}
