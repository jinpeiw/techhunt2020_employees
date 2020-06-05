/**
 * Author: Wong Jin Pei
 * This is an controller advice to handle exceptions
 */
package com.govtech.employees.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.govtech.employees.exception.InvalidInputException;
import com.govtech.employees.responses.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handle Invalid Input Exception 
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(InvalidInputException.class)
	@ResponseBody
	public ResponseEntity<ExceptionResponse> handleInvalidInputException(InvalidInputException ex,
			HttpServletRequest request) {

		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(new Timestamp(System.currentTimeMillis()),
				HttpStatus.BAD_REQUEST.value(), "Invalid Input Exception", ex.getMessage(), request.getRequestURI()),
				HttpStatus.BAD_REQUEST);

	}

}
