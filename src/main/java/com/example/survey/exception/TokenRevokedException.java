package com.example.survey.exception;

public class TokenRevokedException extends ApiException {
    public TokenRevokedException() {
        super("Token revoked");
    }
}
