package com.uniamerica.unijobsbackend.Excessoes;

public class AuthorizationException extends RuntimeException{

    public AuthorizationException(String message) {
        super(message);
    }
}
