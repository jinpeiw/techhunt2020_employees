/**
 * Author: Wong Jin Pei 
 * This service class contains business logic for User Story #3 
 */

package com.govtech.employees.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.govtech.employees.dao.EmployeesRepository;
import com.govtech.employees.exception.InvalidInputException;
import com.govtech.employees.model.Employees;
import com.govtech.employees.responses.SuccessResponse;
import com.govtech.employees.utils.Constants;

@Service
public class BonusQnsService {

	@Autowired
	EmployeesRepository employeesRepository;

	/**
	 * Get employee details by Id
	 * 
	 * @param id
	 * @return
	 */
	public Employees getEmployeeDetails(String id) {
		Employees emp = new Employees();

		emp = employeesRepository.findById(id);

		return emp;

	}

	/**
	 * Delete employee details by Id
	 * 
	 * @param empId
	 * @return
	 * @throws InvalidInputException
	 */
	public SuccessResponse deleteEmployeeDetails(String empId) throws InvalidInputException {

		Employees empExist = employeesRepository.findById(empId);
		SuccessResponse successRes = new SuccessResponse();

		if (empExist == null) {
			throw new InvalidInputException("Error deleting employees details, ID does not exist: " + empId);
		} else {
			employeesRepository.deleteById(empId);
			successRes.setTimestamp(new Timestamp(System.currentTimeMillis()));
			successRes.setStatus("SUCCESS");
			successRes.setMessage("Success delete employee: " + empId);
		}

		return successRes;

	}

	/**
	 * Insert/Update employee details
	 * 
	 * @param emp
	 * @param method
	 * @return
	 * @throws InvalidInputException
	 */
	public Employees setEmployeeDetails(Employees emp, String method) throws InvalidInputException {
		String empId = emp.getId();

		Employees empExist = employeesRepository.findById(empId);

		if (empExist == null && method.equals(Constants.STRING_UPDATE)) {
			throw new InvalidInputException("Error updating employees details, ID does not exist: " + empId);
		} else if (empExist != null && method.equals(Constants.STRING_INSERT)) {
			throw new InvalidInputException("Error creating employees details, ID already exists: " + empId);
		}

		employeesRepository.insertCsvData(empId, emp.getLogin(), emp.getName(), emp.getSalary());

		return emp;

	}

}
