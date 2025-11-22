package com.example.survey.exception;

public class InvalidTokenException extends ApiException {
    public InvalidTokenException() {
        super("Invalid token");
    }
}
