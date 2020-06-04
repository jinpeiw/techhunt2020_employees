package com.govtech.employees.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.govtech.employees.dao.EmployeesRepository;
import com.govtech.employees.exception.InvalidInputException;
import com.govtech.employees.model.Employees;
import com.govtech.employees.responses.DuplicateLoginResponse;
import com.govtech.employees.responses.UploadResponse;
import com.govtech.employees.utils.Constants;
import com.govtech.employees.utils.OffsetBasedPageRequest;

@Service
public class EmployeesService {

	@Autowired
	EmployeesRepository employeesRepository;

	public List<Employees> getEmployeesDashboard(Double minSalary, Double maxSalary, Integer offset, Integer limit,
			String sort) throws InvalidInputException {

		System.out.println(" getEmployeesDashboard ");
		List<Employees> lstEmployees = new ArrayList<Employees>();
		Direction sortDirection = Sort.Direction.ASC;
		String sortField = "id";

		// Get sorting direction and field
		if (null != sort) {
			String dir = sort.substring(0, 1);
			sortField = sort.substring(1);

			if (!Constants.SORT_SYMBOLS.contains(dir) || !Constants.SORT_PARAMS.contains(sortField)) {
				throw new InvalidInputException("Invalid sorting criteria entered.");
			}

			if (dir.equals("-")) {
				sortDirection = Sort.Direction.DESC;
			}
		}

		Pageable pageable = new OffsetBasedPageRequest(offset, limit, Sort.by(sortDirection, sortField));

		// Check if maxSalary is null
		if (null == maxSalary) {
			Page<Employees> pageEmployees = employeesRepository.findAll(pageable);
			lstEmployees = pageEmployees.getContent();

		} else {
			Page<Employees> pageEmployees = employeesRepository.findBySalaryBetween(minSalary, maxSalary, pageable);
			lstEmployees = pageEmployees.getContent();
		}

		return lstEmployees;

	}

	public ResponseEntity<UploadResponse> saveCsvData(MultipartFile file)
			throws IOException, InvalidInputException, IllegalArgumentException {

		System.out.println(" saveCsvData ");

		List<String> lstOfData = new ArrayList<>();
		int noOfSuccess = 0;
		List<String> successIds = new ArrayList<>();
		List<DuplicateLoginResponse> failIds = new ArrayList<>();

		UploadResponse response = new UploadResponse();
		HttpStatus httpStatus = HttpStatus.OK;

		// Check if file is empty
		if (file.isEmpty()) {
			throw new IllegalArgumentException("File is empty.");
		}

		byte[] bytes = file.getBytes();
		ByteArrayInputStream inputFilestream = new ByteArrayInputStream(bytes);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream));
		String line = "";

		while ((line = br.readLine()) != null) {
			if (line.split(",").length != 4) {
				throw new InvalidInputException("Number of columns do not match. Please upload again.");
			}
			if (!line.startsWith(Constants.CSV_COMMENTS_SYMBOL)) {
				lstOfData.add(line);
			}
		}
		lstOfData.remove(0);

		br.close();

		for (int i = 0; i < lstOfData.size(); i++) {
			String[] values = lstOfData.get(i).split(",");

			double tempSalary = Double.parseDouble(values[3]);
			String userId = values[0];
			String login = values[1];

			if (tempSalary < 0) {
				DuplicateLoginResponse failRes = new DuplicateLoginResponse(userId,
						"Salary entered for " + userId + " is below $0.");
				failIds.add(failRes);

			} else {
				if (employeesRepository.findByLogin(userId, login).size() > 0) {
					DuplicateLoginResponse failRes = new DuplicateLoginResponse(userId,
							"Login entered for " + userId + " already exists.");
					failIds.add(failRes);
				} else {
					employeesRepository.insertCsvData(userId, login, values[2], tempSalary);
					noOfSuccess++;
					successIds.add(userId);
				}

			}

		}

		response.setNoOfSuccessRows(noOfSuccess);
		response.setFailIds(failIds);
		response.setSuccessIds(successIds);
		if (failIds.size() > 0) {
			httpStatus = HttpStatus.MULTI_STATUS;
		}

		return new ResponseEntity(response, httpStatus);

	}

}
