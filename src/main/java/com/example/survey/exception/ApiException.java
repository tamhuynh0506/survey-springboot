package com.example.survey.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
