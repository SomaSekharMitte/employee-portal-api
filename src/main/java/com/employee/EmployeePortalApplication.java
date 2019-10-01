package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * EmployeePortalApplication
 * 
 * @author Soma
 * @version v1.0
 * 
 */
@SpringBootApplication
@EnableCaching
public class EmployeePortalApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(EmployeePortalApplication.class, args);
	}
}
