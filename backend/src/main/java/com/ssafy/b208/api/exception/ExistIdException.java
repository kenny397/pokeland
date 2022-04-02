package com.ssafy.b208.api.exception;

public class ExistIdException extends RuntimeException{
    public ExistIdException(String id){
        super("ID is exist "+ id);
    }
}
