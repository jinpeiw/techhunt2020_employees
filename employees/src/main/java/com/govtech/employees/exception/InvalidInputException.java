/**
 * Author: Wong Jin Pei
 * This is a custom exception to handle invalid input
 */
package com.govtech.employees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Component
public class InvalidInputException extends RuntimeException {

	private String message;

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String message) {
		super(message);
		this.message = message;
	}
}
