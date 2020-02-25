package com.starbucks.exception;

import com.starbucks.exception.error.StarbucksError;

import static com.starbucks.exception.error.StarbucksError.INVALID_FIELD_VALUE;

public class InvalidFieldValueException extends RuntimeException {

        private StarbucksError error;

        public InvalidFieldValueException() {
                super(INVALID_FIELD_VALUE.getMessage());
                this.error = INVALID_FIELD_VALUE;
        }

        public InvalidFieldValueException(final String message) {
                super(message);
                this.error = INVALID_FIELD_VALUE;
        }

        public InvalidFieldValueException(final String message, final Throwable cause) {
                super(message, cause);
                this.error = INVALID_FIELD_VALUE;
        }

        public InvalidFieldValueException(final Throwable cause) {
                super(cause);
                this.error = INVALID_FIELD_VALUE;
        }

        protected InvalidFieldValueException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
                super(message, cause, enableSuppression, writableStackTrace);
                this.error = INVALID_FIELD_VALUE;
        }

        public StarbucksError getError() {
                return error;
        }
}
