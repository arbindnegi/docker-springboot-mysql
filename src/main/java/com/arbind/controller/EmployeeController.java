package com.arbind.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arbind.exception.InvalidTransationReferenceException;
import com.arbind.model.Employee;
import com.arbind.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public List<Employee> getEmployees() {

		return empService.getAllEmployees();

	}
	
	@RequestMapping(value = "/insertemployee", method = RequestMethod.POST)
	public void insertEmployee(@RequestBody Employee employee) {

		empService.insertEmployee(employee);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee viewEmployeeById(@PathVariable("id") String id) {
		Employee employee = empService.getEmployee(id);
		if(employee !=null) {
			return employee;
		}

		throw new InvalidTransationReferenceException("Invalid transaction reference provided");
	}

}
