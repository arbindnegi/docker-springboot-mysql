package com.arbind.dao;

import java.util.List;

import com.arbind.model.Employee;

public interface EmployeeDao {
	List<Employee> getAllEmployees();

	void insertEmployee(Employee employee);
	Employee findById (String id);
	
}