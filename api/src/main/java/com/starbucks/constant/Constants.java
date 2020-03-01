package com.starbucks.constant;

public class Constants {
   public static final String EMAIL_VALIDATION_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
   public static final String SIMPLE_DATE_VALIDATION_REGEX = "\\d{4}-[01]\\d-[0-3]\\d";
   public static final String COMPLETE_DATE_VALIDATION_REGEX = "\\d{4}-[01]\\d-[0-3]\\d";
   public static final String PASSWORD_VALIDATION_REGEX = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
   public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
   public static final String COMPLETE_DATE_FORMAT = "yyyy-MM-dd : HH:mm:ss";
   public static final java.time.format.DateTimeFormatter DATE_FORMATTER = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
   public static final String ERROR = "error";
   public static final String DATA = "data";
   public static final String META = "meta";
   public static final String DEBUG = "debug";

   // HTTP Constants
   public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";
   public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
   public static final String HTTP_HEADER_ORIGIN = "Origin";
   public static final String HTTP_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
   public static final String HTTP_HEADER_ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
   public static final String HTTP_HEADER_ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
   public static final String HTTP_HEADER_ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
   public static final String HTTP_HEADER_ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
   public static final String HTTP_HEADER_CRUMB = "X-CSRF-Token";
   public static final String HTTP_HEADER_ACCEPT_LANGUAGE = "Accept-Language";
   public static final String HTTP_HEADER_HOST = "Host";

   public static final String HTTP_METHOD_GET = "GET";
   public static final String HTTP_METHOD_OPTIONS = "OPTIONS";
   public static final String WHITELISTED_DOMAIN = "whiteListedDomains";

}
