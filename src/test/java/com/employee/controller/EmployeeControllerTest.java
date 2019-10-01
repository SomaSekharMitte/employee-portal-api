/**
 * 
 */
package com.employee.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

/**
 * @author Soma
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeService employeeService;

	@Test
	public void should_returnAllEmployees() throws Exception {

		String id = UUID.randomUUID().toString();
		Employee emp = new Employee(id, "firstName", "lastName", 'M', "20-04-1980", "HR Finance");

		List<Employee> empList = new ArrayList<Employee>();
		empList.add(emp);

		when(employeeService.getAllEmployees()).thenReturn(empList);

		this.mockMvc.perform(get("/api/v1/employees/list").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$[0].id", is(empList.get(0).getId())))
				.andExpect(jsonPath("$[0].firstName", is(empList.get(0).getFirstName())))
				.andExpect(jsonPath("$[0].lastName", is(empList.get(0).getLastName())))
				.andExpect(jsonPath("$[0].dateOfBirth", is(empList.get(0).getDateOfBirth())))
				.andExpect(jsonPath("$[0].department", is(empList.get(0).getDepartment())));

	}
}
