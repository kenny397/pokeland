package com.ssafy.b208.api.exception;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(Long id){
        super("Address not found "+ id);
    }
}
