package com.govtech.employees.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.govtech.employees.model.Employees;

@Repository
public interface EmployeesRepository extends CrudRepository<Employees, Long> {

	Page<Employees> findAll(Pageable pageable);

	Page<Employees> findBySalaryBetween(double minSalary, double maxSalary, Pageable pageable);

	@Query(value = "select * from govtech_employees where login = :login and id != :id ", nativeQuery = true)
	List<Employees> findByLogin(@Param("id") String id, @Param("login") String login);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "replace into govtech_employees values (:id ,:login,:name, :salary)", nativeQuery = true)
	void insertCsvData(@Param("id") String id, @Param("login") String login, @Param("name") String name,
			@Param("salary") double salary);

}
