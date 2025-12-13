package com.example.survey.exception;

public class NotFoundException extends ApiException {
    public NotFoundException(String entityName) {
        super(entityName + " not found");
    }
}
