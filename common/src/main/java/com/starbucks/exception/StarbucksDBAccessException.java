package com.starbucks.exception;

import com.starbucks.exception.error.StarbucksError;

public class StarbucksDBAccessException extends RuntimeException {

    private StarbucksError error;

    public StarbucksDBAccessException() {
        super();
    }

    public StarbucksDBAccessException(final String message) {
        super(message);
    }

    public StarbucksDBAccessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public StarbucksDBAccessException(final Throwable cause) {
        super(cause);
    }

    protected StarbucksDBAccessException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
