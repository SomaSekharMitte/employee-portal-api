package com.employee.controller;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.exceptions.EmployeeServiceException;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * EmployeeController
 * 
 * @author Soma
 * @version v1.0
 * 
 */

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "*")
@Slf4j
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * Creates a employee
	 * 
	 * @param emp
	 * @return
	 * @throws MissingServletRequestParameterException
	 */
	@ApiOperation(value = "Create employee in the system", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Employee created successfully", response = String.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Employee data not found") })
	@PostMapping("/")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee emp)
			throws MissingServletRequestParameterException {
		log.info("Create employee called");
		String empId = employeeService.createEmployee(emp);
		return new ResponseEntity<>(empId, HttpStatus.OK);
	}

	/**
	 * Get list of all employees
	 * 
	 * @return
	 * @throws EmployeeServiceException
	 */
	@ApiOperation(value = "View a list of available employees in the system", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of employees returned successfully", response = List.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Employee data not found") })
	@GetMapping("/list")
	public ResponseEntity<Object> getEmployeeList() {
		log.info("Results: {} ", employeeService.getAllEmployees());
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	/**
	 * Get employee details by Id
	 * 
	 * @param id
	 * @return
	 * @throws MissingServletRequestParameterException
	 */
	@ApiOperation(value = "View employee details by id in the system", response = Employee.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Employee details returned successfully", response = Employee.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Employee data not found") })
	@GetMapping("/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable("id") String id)
			throws MissingServletRequestParameterException {
		Employee employeeById = employeeService.getEmployeeById(id);
		log.info("Result By Id: {} is {} ", id, employeeById);
		return new ResponseEntity<>(employeeById, HttpStatus.OK);
	}

	/**
	 * Get employees details by department
	 * 
	 * @param department
	 * @return
	 * @throws EmployeeServiceException
	 */
	@ApiOperation(value = "Get employees pertaining to a department", response = Employee.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of employees by department returned successfully", response = Employee.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Employee data not found") })
	@GetMapping(value = "/department/{department}")
	public ResponseEntity<Object> getEmployeeByDepartment(@PathVariable("department") String department) {
		log.info("Result By Id: {} is: {}", department, null);
		return new ResponseEntity<>(new Employee(), HttpStatus.OK);
	}

	/**
	 * Delete an employee by id
	 * 
	 * @param id
	 * @return
	 * @throws EmployeeServiceException
	 * @throws MissingServletRequestParameterException
	 */
	@ApiOperation(value = "Delete employee from the System", response = Employee.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Employee details deleted successfully", response = Employee.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Employee data not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) throws MissingServletRequestParameterException {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>("Employee is deleted successsfully", HttpStatus.OK);
	}
}
