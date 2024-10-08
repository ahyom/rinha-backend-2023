package com.soave.backend_performance_challenge.model.exception;

public class GenericErrorException extends RuntimeException {
    public GenericErrorException(final String message, final Throwable cause) {
        super(message + ": " + cause);
    }
}
