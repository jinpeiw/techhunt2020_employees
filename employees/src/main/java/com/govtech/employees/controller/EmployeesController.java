/**
 * Author: Wong Jin Pei
 * This is an controller class to contain services for user story #1 and #2
 */
package com.govtech.employees.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.govtech.employees.exception.InvalidInputException;
import com.govtech.employees.model.Employees;
import com.govtech.employees.responses.Results;
import com.govtech.employees.responses.UploadResponse;
import com.govtech.employees.services.EmployeesService;

@RestController
public class EmployeesController {

	@Autowired
	EmployeesService employeesService;

	/**
	 * Get list of employees with GET /users
	 * 
	 * @param minSalary
	 * @param maxSalary
	 * @param offset
	 * @param limit
	 * @param sort
	 * @return
	 * @throws InvalidInputException
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Results getEmployeesDashboard(@RequestParam(name = "minSalary") Double minSalary,
			@RequestParam(name = "maxSalary") Double maxSalary, @RequestParam(name = "offset") Integer offset,
			@RequestParam(name = "limit") Integer limit, @RequestParam(name = "sort") String sort)
			throws InvalidInputException {

		Results results = new Results();
		List<Employees> lstEmployees = employeesService.getEmployeesDashboard(minSalary, maxSalary, offset, limit,
				sort);
		results.setResults(lstEmployees);

		return results;

	}

	/**
	 * Upload CSV file with POST /users/upload
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws InvalidInputException
	 * @throws IllegalArgumentException
	 */
	@RequestMapping(value = "/users/upload", method = RequestMethod.POST)
	public ResponseEntity<UploadResponse> processUpload(@RequestParam MultipartFile file)
			throws IOException, InvalidInputException, IllegalArgumentException {

		ResponseEntity<UploadResponse> response = employeesService.saveCsvData(file);
		return response;
	}

}
