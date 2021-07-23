package com.arbind.service;

import java.util.List;

import com.arbind.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void insertEmployee(Employee employee);
	Employee getEmployee(String id);

}