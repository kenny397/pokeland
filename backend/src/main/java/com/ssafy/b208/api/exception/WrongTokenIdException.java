package com.ssafy.b208.api.exception;

public class WrongTokenIdException extends RuntimeException {
    public WrongTokenIdException(Long tokenId) {
        super(tokenId + " is wrong token id");
    }
}
