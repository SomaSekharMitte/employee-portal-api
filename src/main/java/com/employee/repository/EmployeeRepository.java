/**
 * 
 */
package com.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

/**
 * EmployeeRepository
 * 
 * @author Soma
 * @version v1.0
 * 
 */

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
