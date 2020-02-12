package com.starbucks.constant;

public class Constants {
   public static final String EMAIL_VALIDATION_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
   public static final String SIMPLE_DATE_VALIDATION_REGEX = "\\d{4}-[01]\\d-[0-3]\\d";
   public static final String PASSWORD_VALIDATION_REGEX = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
   public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";

}
