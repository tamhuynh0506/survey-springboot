package com.example.survey;

import java.time.LocalDateTime;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<T> {

    private final LocalDateTime timestamp;
    private final int status;
    private final String message;
    private T data; // Generic type T for the payload

    private ApiResponse(HttpStatus status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    private ApiResponse(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.message = message;
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        ApiResponse<T> responseBody = new ApiResponse<>(HttpStatus.OK, message, data);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /** Returns a 201 CREATED response with data. */
    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        ApiResponse<T> responseBody = new ApiResponse<>(HttpStatus.CREATED, message, data);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    /** Returns a standard error response (e.g., 400, 404, 500) without data. */
    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message) {
        ApiResponse<T> responseBody = new ApiResponse<>(status, message);
        return new ResponseEntity<>(responseBody, status);
    }

    /** Returns a invalid method arguments error response with error fields. */
    public static <T> ResponseEntity<ApiResponse<T>> invalidMethodArguments(String message, T data) {
        ApiResponse<T> responseBody = new ApiResponse<>(HttpStatus.BAD_REQUEST, message, data);
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}