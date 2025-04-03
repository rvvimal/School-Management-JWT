package com.school_management.exception;

public class BadRequestServiceAlertException extends RuntimeException {
    public BadRequestServiceAlertException(String message) {
        super(message);
    }
}
