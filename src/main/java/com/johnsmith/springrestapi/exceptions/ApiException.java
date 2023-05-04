package com.johnsmith.SpringCrudRestApi.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiException extends RuntimeException {
    private HttpStatus statusCode;
    private String message;
    private Object errors;

    public ApiException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.errors = null;
    }

    public ApiException(HttpStatus statusCode, String message, Object errors) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
    }
}
