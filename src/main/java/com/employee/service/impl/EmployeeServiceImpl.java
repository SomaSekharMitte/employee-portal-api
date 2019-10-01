
/**
 * 
 */
package com.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.employee.constants.EmployeeServiceConstants;
import com.employee.exceptions.ApiError;
import com.employee.exceptions.EmployeeServiceException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

/**
 * EmployeeServiceImpl
 * 
 * @author Soma
 * @version v1.0
 * 
 */

@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	/**
	 * Get employee by employee Id
	 */
	@Override
	public Employee getEmployeeById(String id) throws MissingServletRequestParameterException {

		if (id.isEmpty()) {
			throw new MissingServletRequestParameterException("id", EmployeeServiceConstants.EMPLOYEE_ID_IS_MISSING);
		}
		Optional<Employee> empById = employeeRepository.findById(id);
		Employee employee = null;

		if (empById.isPresent()) {
			employee = empById.get();
		}

		if (employee == null) {
			log.error("Employee not found with Id: {} ", id);
			HttpStatus status = HttpStatus.NOT_FOUND;
			String message = EmployeeServiceConstants.EMPLOYEE_NOT_FOUND;
			ApiError apiErr = new ApiError(status, message);
			throw new EmployeeServiceException(EmployeeServiceConstants.EMP_ID_WRONG, apiErr);
		}

		return employee;
	}

	/**
	 * Get employee by Department
	 */
	@Override
	public List<Employee> getEmployeesByDepartment(String department) throws MissingServletRequestParameterException {
		
		if (department.isEmpty()) {
			throw new MissingServletRequestParameterException("department", "Department can not be null.");
		}
		
		return new ArrayList<>();
	}

	/**
	 * Create a employee
	 */
	@Override
	public String createEmployee(Employee emp) throws MissingServletRequestParameterException {

		if (emp.getFirstName().isEmpty() || emp.getLastName().isEmpty()) {
			throw new MissingServletRequestParameterException("firstName & lastName", "Names can not be null.");
		}

		if (emp.getDateOfBirth().isEmpty()) {
			throw new MissingServletRequestParameterException("dataOfBirth", " DOB can not be null.");
		}

		Employee empDetails = employeeRepository.save(emp);
		return empDetails.getId();
	}

	/**
	 * Update a employee
	 * TODO
	 */
	@Override
	public Employee updateEmployee(Employee emp) {
		return null;
	}

	/**
	 * Delete employee by employee Id
	 */
	@Override
	public void deleteEmployee(String id) throws MissingServletRequestParameterException {

		if (id.isEmpty()) {
			throw new MissingServletRequestParameterException("EmployeeId",
					EmployeeServiceConstants.EMPLOYEE_ID_IS_MISSING);
		}

		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent()) {
			employeeRepository.deleteById(id);
		} else {
			log.error("Employee not found with Id: {} ", id);
			HttpStatus status = HttpStatus.NOT_FOUND;
			String message = EmployeeServiceConstants.EMPLOYEE_NOT_FOUND;
			ApiError apiErr = new ApiError(status, message);
			throw new EmployeeServiceException(EmployeeServiceConstants.EMP_ID_WRONG, apiErr);
		}
	}
}