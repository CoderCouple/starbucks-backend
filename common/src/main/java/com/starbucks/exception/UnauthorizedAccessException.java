package com.starbucks.exception;

import com.starbucks.exception.error.StarbucksError;

import static com.starbucks.exception.error.StarbucksError.USER_NOT_AUTHORIZED;

public class UnauthorizedAccessException extends RuntimeException {

    private StarbucksError error;

    public UnauthorizedAccessException() {
        super(USER_NOT_AUTHORIZED.getMessage());
        this.error = USER_NOT_AUTHORIZED;
    }

    public UnauthorizedAccessException(final String message) {
        super(message);
        this.error = USER_NOT_AUTHORIZED;
    }

    public UnauthorizedAccessException(final String message, final Throwable cause) {
        super(message, cause);
        this.error = USER_NOT_AUTHORIZED;
    }

    public UnauthorizedAccessException(final Throwable cause) {
        super(cause);
        this.error = USER_NOT_AUTHORIZED;
    }

    protected UnauthorizedAccessException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = USER_NOT_AUTHORIZED;
    }

    public StarbucksError getError() {
        return error;
    }
}
