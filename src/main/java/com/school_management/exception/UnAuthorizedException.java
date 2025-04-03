package com.school_management.exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException (String message){
        super(message);
    }
}
