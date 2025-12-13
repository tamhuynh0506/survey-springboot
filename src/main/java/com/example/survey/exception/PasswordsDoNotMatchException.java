package com.example.survey.exception;

import org.springframework.http.HttpStatus;

public class PasswordsDoNotMatchException extends ApiException {
    public PasswordsDoNotMatchException() {
        super(HttpStatus.UNAUTHORIZED, "Passwords do not match");
    }
}
