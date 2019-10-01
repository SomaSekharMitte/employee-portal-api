package com.employee.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * AppExceptionHandler
 * 
 * @author Soma
 * @version v1.0
 * 
 */

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

	/**
	 * DataAccessException Exception Handler
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<Object> handleDataAccessException(DataAccessException exception) {
		log.error("DataAccessAxception caught, Details:- {}", exception.getLocalizedMessage());
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(),
				exception.getLocalizedMessage());
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * MissingServletRequestParameterException Exception Handler
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> missingServletRequestParameterException(
			MissingServletRequestParameterException exception) {
		String error = exception.getParameterName() + " parameter is missing";
		log.error("MissingServletRequestParameterException caught, Details:- {}", exception.getLocalizedMessage());

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * Application Exception Handler
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(EmployeeServiceException.class)
	public ResponseEntity<Object> handleEmployeeServiceException(EmployeeServiceException exception) {
		log.error("Exception caught, Details:- {}", exception.getLocalizedMessage());

		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getApiError().getMessage(),
				"Server Exception: " + exception.getErrorDesc());
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * Default Exception Handler
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ Exception.class, RuntimeException.class })
	public ResponseEntity<Object> handleException(Exception exception) {
		log.error("Exception caught, Details:- {}", exception.getLocalizedMessage());

		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(),
				"Something went wrong in the server: " + exception.getMessage());
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
