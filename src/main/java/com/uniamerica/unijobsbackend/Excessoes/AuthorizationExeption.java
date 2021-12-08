package com.uniamerica.unijobsbackend.Excessoes;

public class AuthorizationExeption extends RuntimeException{

    public AuthorizationExeption(String message) {
        super(message);
    }
}
