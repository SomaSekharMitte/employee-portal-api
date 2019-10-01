package com.employee.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * EmployeeServiceException
 * 
 * @author Soma
 * @version v1.0
 * 
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorDesc;
	private ApiError apiError;

	public EmployeeServiceException(String errDesc) {
		super(errDesc);
	}

	public EmployeeServiceException(String errDesc, ApiError apiErr) {
		this.errorDesc = errDesc;
		this.apiError = apiErr;
	}

}
