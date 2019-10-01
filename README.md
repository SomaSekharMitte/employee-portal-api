# employee-portal-service API

This repo contains api for Employee Portal Management System.

## Overview

## Contents

## Request Mapping

### API/RPC

http paths to Employee portal services. The mapping table can be seen below.

URLs are mapped as follows:

Path	|	Service	|	Method
----	|	----	|	----
/api/v1/employees/list	|	com.employee.controller	|	Controller.getEmployeeList
/api/v1/employees/	|	com.employee.controller	|	Controller.createEmployee
/api/v1/employees/{id}	|	com.employee.controller	|	Controller.getEmployeeById
/api/v1/employees/department/{departmentName}	|	com.employee.controller	|	Controller.getEmployeeByDepartment
/api/v1/employees/{id}	|	com.employee.controller	|	Controller.delete
