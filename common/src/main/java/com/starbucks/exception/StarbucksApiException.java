package com.starbucks.exception;

import com.starbucks.exception.error.StarbucksError;

import static com.starbucks.exception.error.StarbucksError.INTERNAL_SERVER_ERROR;

public class StarbucksApiException extends RuntimeException {

    private StarbucksError error;

    public StarbucksApiException() {
        super(INTERNAL_SERVER_ERROR.getMessage());
        this.error = INTERNAL_SERVER_ERROR;
    }

    public StarbucksApiException(final String message) {
        super(message);
        this.error = INTERNAL_SERVER_ERROR;
    }

    public StarbucksApiException(final String message, final Throwable cause) {
        super(message, cause);
        this.error = INTERNAL_SERVER_ERROR;
    }

    public StarbucksApiException(final Throwable cause) {
        super(cause);
        this.error = INTERNAL_SERVER_ERROR;
    }

    protected StarbucksApiException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = INTERNAL_SERVER_ERROR;
    }

    public StarbucksError getError() {
        return error;
    }
}
