package com.starbucks.exception;

import com.starbucks.exception.error.StarbucksError;

import static com.starbucks.exception.error.StarbucksError.MISSING_REQUIRED_FIELD;

public class MissingRequiredFieldException extends RuntimeException {

    private StarbucksError error;

    public MissingRequiredFieldException() {
        super(MISSING_REQUIRED_FIELD.getMessage());
        this.error = MISSING_REQUIRED_FIELD;
    }

    public MissingRequiredFieldException(final String message) {
        super(message);
        this.error = MISSING_REQUIRED_FIELD;
    }

    public MissingRequiredFieldException(final String message, final Throwable cause) {
        super(message, cause);
        this.error = MISSING_REQUIRED_FIELD;
    }

    public MissingRequiredFieldException(final Throwable cause) {
        super(cause);
        this.error = MISSING_REQUIRED_FIELD;
    }

    protected MissingRequiredFieldException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = MISSING_REQUIRED_FIELD;
    }

    public StarbucksError getError() {
        return error;
    }
}
