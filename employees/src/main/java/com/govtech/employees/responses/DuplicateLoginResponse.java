package com.govtech.employees.responses;

import org.springframework.stereotype.Component;

@Component
public class DuplicateLoginResponse {

	private String id;
	private String message;

	public DuplicateLoginResponse() {
		super();
	}

	public DuplicateLoginResponse(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
