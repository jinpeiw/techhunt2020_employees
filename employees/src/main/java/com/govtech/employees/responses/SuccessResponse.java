/**
 * Author: Wong Jin Pei
 * This is an entity to contain success response
 */
package com.govtech.employees.responses;

import java.sql.Timestamp;

public class SuccessResponse {

	private Timestamp timestamp;
	private String status;
	private String message;

	public SuccessResponse() {
		super();
	}

	public SuccessResponse(Timestamp timestamp, String status, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
