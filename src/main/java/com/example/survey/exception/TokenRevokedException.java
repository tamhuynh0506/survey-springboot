package com.example.survey.exception;

import org.springframework.http.HttpStatus;

public class TokenRevokedException extends ApiException {
    public TokenRevokedException() {
        super(HttpStatus.UNAUTHORIZED, "Token revoked");
    }
}
