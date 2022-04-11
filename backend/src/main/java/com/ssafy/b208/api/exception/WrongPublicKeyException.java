package com.ssafy.b208.api.exception;

public class WrongPublicKeyException extends RuntimeException {
    public WrongPublicKeyException(String publicKey) {
        super(publicKey + " is wrong public key");
    }
}
