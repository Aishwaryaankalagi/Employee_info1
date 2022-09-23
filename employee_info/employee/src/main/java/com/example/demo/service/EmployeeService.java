package com.example.demo.service;
import java.util.List;

import com.example.demo.model.Employee_info;


public interface EmployeeService {

	Employee_info saveEmployee(Employee_info employee_info);
	List<Employee_info> getAllEmployees();
	Employee_info getEmployeeById(long id);
	Employee_info updateEmployee(Employee_info employee_info, long id);
	void deleteEmployee(long id);
}
