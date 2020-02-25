package com.starbucks.exception;

import com.starbucks.exception.error.StarbucksError;

import static com.starbucks.exception.error.StarbucksError.NO_DATA_FOUND;

public class NotFoundException extends RuntimeException {

    private StarbucksError error;

    public NotFoundException() {
        super(NO_DATA_FOUND.getMessage());
        this.error = NO_DATA_FOUND;
    }

    public NotFoundException(final String message) {
        super(message);
        this.error = NO_DATA_FOUND;
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
        this.error = NO_DATA_FOUND;
    }

    public NotFoundException(final Throwable cause) {
        super(cause);
        this.error = NO_DATA_FOUND;
    }

    protected NotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = NO_DATA_FOUND;
    }

    public StarbucksError getError() {
        return error;
    }
}
