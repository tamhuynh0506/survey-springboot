package com.example.survey.exception;

public class NotFoundException extends ApiException {
    public NotFoundException() {
        super("Not found");
    }
}
