/**
 * Author: Wong Jin Pei
 * This is an controller class to contain services for user story #3
 */
package com.govtech.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.govtech.employees.exception.InvalidInputException;
import com.govtech.employees.model.Employees;
import com.govtech.employees.responses.SuccessResponse;
import com.govtech.employees.services.BonusQnsService;
import com.govtech.employees.utils.Constants;

@RestController
public class BonusQnsController {

	@Autowired
	BonusQnsService bonusQnsService;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public Employees getEmpDetailsById(@PathVariable(name = "id") String id) {

		Employees emp = bonusQnsService.getEmployeeDetails(id);

		return emp;

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
	public Employees createEmpDetails(@PathVariable(name = "id") String id, @RequestBody Employees employees)
			throws InvalidInputException {

		Employees emp = bonusQnsService.setEmployeeDetails(employees, Constants.STRING_INSERT);

		return emp;

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public SuccessResponse deleteEmpDetails(@PathVariable(name = "id") String id) throws InvalidInputException {

		SuccessResponse emp = bonusQnsService.deleteEmployeeDetails(id);

		return emp;

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH)
	public Employees patchEmpDetails(@PathVariable(name = "id") String id, @RequestBody Employees employees)
			throws InvalidInputException {

		Employees emp = bonusQnsService.setEmployeeDetails(employees, Constants.STRING_UPDATE);

		return emp;

	}

}
