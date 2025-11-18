package com.example.survey.exception;

public class EmailAlreadyExistsException extends ApiException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
