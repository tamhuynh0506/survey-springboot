package com.example.survey.exception;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException() {
        super("User not found");
    }
}
