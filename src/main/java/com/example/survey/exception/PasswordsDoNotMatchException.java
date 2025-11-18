package com.example.survey.exception;

public class PasswordsDoNotMatchException extends ApiException {
    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
