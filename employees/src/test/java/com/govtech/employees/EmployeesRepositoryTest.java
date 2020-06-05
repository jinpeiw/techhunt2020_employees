/**
 * Author: Wong Jin Pei 
 * Unit testing for Data JPA
 */
package com.govtech.employees;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.govtech.employees.dao.EmployeesRepository;
import com.govtech.employees.model.Employees;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeesRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeesRepository employeesRepository;

	@Test
	public void whenFindById_thenReturnEmployee() {
		// given
		Employees t0002 = new Employees();
		t0002.setId("t0002");
		t0002.setLogin("test1");
		t0002.setName("test2");
		t0002.setSalary(4000d);
		entityManager.persist(t0002);
		entityManager.flush();

		// when
		Employees found = employeesRepository.findById(t0002.getId());

		// then
		assertThat(found.getId()).isEqualTo(t0002.getId());
	}

	@Test
	public void whenDeleteById_thenReturnNull() {
		Employees found = employeesRepository.findById("t0001");
		assertThat(found).isNotNull();

		employeesRepository.deleteById("t0001");

		Employees found2 = employeesRepository.findById("t0001");
		assertThat(found2).isNull();
	}

	@Test
	public void whenInsertCsvData_thenReturnEmployees() {

		employeesRepository.insertCsvData("t0003", "test3", "test3", 3333);

		Employees found = employeesRepository.findById("t0003");
		assertThat(found).isNotNull();
	}

	@Test
	public void whenUpdateCsvData_thenReturnEmployees() {
		Employees t0004 = new Employees();
		t0004.setId("t0004");
		t0004.setLogin("test4");
		t0004.setName("test4");
		t0004.setSalary(4000d);
		entityManager.persist(t0004);
		entityManager.flush();

		String newLoginValue = "test4-1";

		employeesRepository.insertCsvData("t0004", newLoginValue, "test4-1", 3333);

		Employees found = employeesRepository.findById(t0004.getId());
		assertThat(found.getLogin()).isEqualTo(newLoginValue);
	}

	@Test
	public void whenFindLoginExistForOtherIds_thenReturnNone() {
		Employees t0005 = new Employees();
		t0005.setId("t0005");
		t0005.setLogin("test5");
		t0005.setName("test5");
		t0005.setSalary(2000d);
		entityManager.persist(t0005);
		entityManager.flush();

		List<Employees> found = employeesRepository.findByLogin(t0005.getId(), t0005.getLogin());

		assertThat(found.size()).isEqualTo(0);

	}

	@Test
	public void whenFindLoginExistForOtherIds_thenReturnValidEmployee() {
		Employees t0006 = new Employees();
		t0006.setId("t0006");
		t0006.setLogin("test6");
		t0006.setName("test6");
		t0006.setSalary(2000d);
		entityManager.persist(t0006);
		entityManager.flush();

		List<Employees> found = employeesRepository.findByLogin("t0005", t0006.getLogin());

		assertThat(found.size()).isEqualTo(1);

	}

}
