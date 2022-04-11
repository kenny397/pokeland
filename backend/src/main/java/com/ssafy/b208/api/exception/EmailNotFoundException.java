package com.ssafy.b208.api.exception;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String email){
        super("EmailNotFound "+ email);
    }
}
