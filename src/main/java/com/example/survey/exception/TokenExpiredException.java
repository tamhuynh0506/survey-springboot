package com.example.survey.exception;

public class TokenExpiredException extends ApiException {
    public TokenExpiredException() {
        super("Token expired");
    }
}
