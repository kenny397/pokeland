package com.ssafy.b208.api.exception;

public class NonExistentPokedexIdException extends RuntimeException {
    public NonExistentPokedexIdException(Long id) {
        super(id + " is non-existent pokedex id");
    }
}
