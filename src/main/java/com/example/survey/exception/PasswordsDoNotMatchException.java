package com.example.survey.exception;

public class PasswordsDoNotMatchException extends ApiException {
    public PasswordsDoNotMatchException() {
        super("Passwords do not match");
    }
}
