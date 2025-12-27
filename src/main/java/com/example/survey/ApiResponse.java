package com.example.survey;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<T> {

    private final LocalDateTime timestamp;
    private final int status;
    private final String message;
    private final T data; // Generic type T for the payload

    public ApiResponse(HttpStatus status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return build(HttpStatus.OK, message, data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        return build(HttpStatus.OK, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success() {
        return build(HttpStatus.OK, "Success", null);
    }

    /** Returns a 201 CREATED response with data. */
    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        return build(HttpStatus.CREATED, message, data);
    }

    /** Returns a standard error response (e.g., 400, 404, 500) without data. */
    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message) {
        return build(status, message, null);
    }

    /** Returns a invalid data error response. */
    public static <T> ResponseEntity<ApiResponse<T>> invalidData(String message, T errors) {
        return build(HttpStatus.BAD_REQUEST, message, errors);
    }

    private static <T> ResponseEntity<ApiResponse<T>> build(HttpStatus status, String message, T data) {
        ApiResponse<T> body = new ApiResponse<>(status, message, data);
        return new ResponseEntity<>(body, status);
    }

    public String toJson(ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert ApiResponse to JSON", e);
        }
    }
}