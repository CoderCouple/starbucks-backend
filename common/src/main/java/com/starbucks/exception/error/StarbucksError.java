package com.starbucks.exception.error;

import com.starbucks.localization.Localization;

import static com.starbucks.constant.Constants.DEFAULT_LOCALE;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public enum Error {

    BAD_REQUEST(SC_BAD_REQUEST,"BAD_REQUEST"),
    DATABASE_READ_FAILED(SC_INTERNAL_SERVER_ERROR,"DATABASE_READ_FAILED"),
    DATABASE_WRITE_FAILED(SC_INTERNAL_SERVER_ERROR,"DATABASE_WRITE_FAILED"),
    DEFAULT_ERROR(SC_INTERNAL_SERVER_ERROR,"DEFAULT_ERROR"),
    INTERNAL_SERVER_ERROR(SC_INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR"),
    INVALID_PARAMETERS(SC_BAD_REQUEST,"INVALID_PARAMETERS"),
    MISSING_PARAMETERS(SC_BAD_REQUEST,"MISSING_PARAMETERS"),
    MISSING_REQUIRED_FIELD(SC_BAD_REQUEST,"MISSING_REQUIRED_FIELD"),
    EXPIRED_TOKEN(SC_FORBIDDEN,"EXPIRED_TOKEN"),
    MISSING_TOKEN(SC_FORBIDDEN,"MISSING_TOKEN"),
    NO_DATA_FOUND(SC_BAD_REQUEST, "NO_DATA_FOUND"),
    USER_NOT_FOUND(SC_UNAUTHORIZED,"USER_NOT_FOUND"),
    USER_NOT_AUTHORIZED(SC_UNAUTHORIZED,"USER_NOT_AUTHORIZED");

    private int code;
    private String key;

    Error(int code, String key) {
        this.code = code;
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }


    public String getMessage() {
        return getKey() + " - " + Localization.getLocalizedMsg(getKey(), DEFAULT_LOCALE);
    }

    public String getMessage(final Object... msgVariables) {
        return Localization.getLocalizedMsg(getKey(), DEFAULT_LOCALE, msgVariables);
    }

}
