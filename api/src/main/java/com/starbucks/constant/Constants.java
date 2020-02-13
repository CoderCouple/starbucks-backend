package com.starbucks.constant;

public class Constants {
   public static final String EMAIL_VALIDATION_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
   public static final String SIMPLE_DATE_VALIDATION_REGEX = "\\d{4}-[01]\\d-[0-3]\\d";
   public static final String PASSWORD_VALIDATION_REGEX = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
   public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";

   public static final String ERROR = "error";
   public static final String DATA = "data";
   public static final String META = "meta";
   public static final String DEBUG = "debug";

}
