package com.texsmartly.PayrollPage.exceptionHandling;


public class PayRollNotFoundException extends RuntimeException {
    public PayRollNotFoundException(String message) {
        super(message);
    }

    public PayRollNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public PayRollNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}