package com.school_management.exception;

public class JWTSignatureException extends RuntimeException {
    public JWTSignatureException(String message) {
        super(message);
    }
}
