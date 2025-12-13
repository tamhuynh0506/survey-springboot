package com.example.survey.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends ApiException {
    public EmailAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "Email already exists");
    }
}
