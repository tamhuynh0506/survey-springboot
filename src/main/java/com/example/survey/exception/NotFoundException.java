package com.example.survey.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    public NotFoundException(Class<?> entityClass) {
        super(HttpStatus.NOT_FOUND, entityClass.getSimpleName() + " not found");
    }
}
