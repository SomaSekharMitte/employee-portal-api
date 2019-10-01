package com.employee.service;

import java.util.List;

import org.springframework.web.bind.MissingServletRequestParameterException;

import com.employee.model.Employee;

/**
 * EmployeeService
 * 
 * @author Soma
 * @version v1.0
 * 
 */
public interface EmployeeService {

	/**
	 * Create employee
	 * 
	 * @param emp
	 * @return
	 * @throws MissingServletRequestParameterException
	 */
	String createEmployee(Employee emp) throws MissingServletRequestParameterException;

	/**
	 * Update employee
	 * 
	 * @param emp
	 * @return
	 */
	Employee updateEmployee(Employee emp) throws MissingServletRequestParameterException;

	/**
	 * Delete employee with employee id
	 * 
	 * @param id
	 * @throws MissingServletRequestParameterException
	 */
	void deleteEmployee(String id) throws MissingServletRequestParameterException;

	/**
	 * Get all employees
	 * 
	 * @return list of employees
	 */
	List<Employee> getAllEmployees();

	/**
	 * Get employee by id
	 * 
	 * @param id
	 * @return
	 * @throws MissingServletRequestParameterException
	 */
	Employee getEmployeeById(String id) throws MissingServletRequestParameterException;

	/**
	 * Get all employees by department
	 * 
	 * @param department
	 * @return
	 * @throws MissingServletRequestParameterException 
	 */
	List<Employee> getEmployeesByDepartment(String department) throws MissingServletRequestParameterException;

}
