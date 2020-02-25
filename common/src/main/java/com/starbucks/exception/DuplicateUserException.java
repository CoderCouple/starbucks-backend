package com.starbucks.exception;

import com.starbucks.exception.error.StarbucksError;

import static com.starbucks.exception.error.StarbucksError.DUPLICATE_USER_FOUND;

public class DuplicateUserException extends RuntimeException {

    private StarbucksError error;

    public DuplicateUserException() {
        super(DUPLICATE_USER_FOUND.getMessage());
        this.error = DUPLICATE_USER_FOUND;
    }

    public DuplicateUserException(final String message) {
        super(message);
        this.error = DUPLICATE_USER_FOUND;
    }

    public DuplicateUserException(final String message, final Throwable cause) {
        super(message, cause);
        this.error = DUPLICATE_USER_FOUND;
    }

    public DuplicateUserException(final Throwable cause) {
        super(cause);
        this.error = DUPLICATE_USER_FOUND;
    }

    protected DuplicateUserException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = DUPLICATE_USER_FOUND;
    }

    public StarbucksError getError() {
        return error;
    }
}
