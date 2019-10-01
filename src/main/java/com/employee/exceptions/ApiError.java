package com.employee.exceptions;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * ApiError
 * 
 * @author Soma
 * @version v1.0
 * 
 */

@Data
public class ApiError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;
	private List<String> errors;

	public ApiError(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ApiError(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(error);
	}

	public ApiError(HttpStatus status, String validationMessage) {
		super();
		this.status = status;
		this.message = validationMessage;
	}

}
