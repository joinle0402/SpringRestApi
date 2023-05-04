package com.johnsmith.SpringCrudRestApi.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ExceptionResponse {
	private HttpStatus statusCode;
	private String message;
	private Object errors;
	
	public ExceptionResponse(HttpStatus statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
		this.errors = null;
	}
	
	public ExceptionResponse(HttpStatus statusCode, String message, Object errors) {
		this.statusCode = statusCode;
		this.message = message;
		this.errors = errors;
	}
}
