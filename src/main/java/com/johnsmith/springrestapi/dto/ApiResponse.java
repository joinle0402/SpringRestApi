package com.johnsmith.SpringCrudRestApi.dto;

import org.springframework.http.HttpStatus;

public record ApiResponse(HttpStatus statusCode, String message, Object data) {

}
