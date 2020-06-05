/**
 * Author: Wong Jin Pei
 * This is an entity to contain upload response
 */
package com.govtech.employees.responses;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UploadResponse {

	private int noOfSuccessRows;
	private List<String> successIds;
	private List<DuplicateLoginResponse> failIds;

	public UploadResponse() {
		super();
	}

	public int getNoOfSuccessRows() {
		return noOfSuccessRows;
	}

	public void setNoOfSuccessRows(int noOfSuccessRows) {
		this.noOfSuccessRows = noOfSuccessRows;
	}

	public List<String> getSuccessIds() {
		return successIds;
	}

	public void setSuccessIds(List<String> successIds) {
		this.successIds = successIds;
	}

	public List<DuplicateLoginResponse> getFailIds() {
		return failIds;
	}

	public void setFailIds(List<DuplicateLoginResponse> failIds) {
		this.failIds = failIds;
	}

}
