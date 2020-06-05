/**
 * Author: Wong Jin Pei
 * This is an entity to contain results response
 */
package com.govtech.employees.responses;

import java.util.List;

import org.springframework.stereotype.Component;

import com.govtech.employees.model.Employees;

@Component
public class Results {

	private List<Employees> results;

	public Results() {
		super();
	}

	public List<Employees> getResults() {
		return results;
	}

	public void setResults(List<Employees> results) {
		this.results = results;
	}

}
