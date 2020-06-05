/**
 * Author: Wong Jin Pei
 * This is an entity to contain exception response
 */
package com.govtech.employees.responses;

import java.sql.Timestamp;

public class ExceptionResponse {

	private Timestamp timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

	public ExceptionResponse() {
		super();
	}

	public ExceptionResponse(Timestamp timestamp, int status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
