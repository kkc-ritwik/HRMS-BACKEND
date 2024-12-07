package com.texsmartly.PayrollPage.exceptionHandling;

public class GenericException extends RuntimeException {

    public GenericException(String message) {
        super(message);
    }

    public GenericException(Throwable throwable) {
        super(throwable);
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
