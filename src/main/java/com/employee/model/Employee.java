package com.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Employee
 * 
 * @author Soma
 * @version v1.0
 * 
 */

@Data
@ToString
@AllArgsConstructor
@Document
public class Employee {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private char gender;
	private String dateOfBirth;
	private String department;

	public Employee() {
	}
}
