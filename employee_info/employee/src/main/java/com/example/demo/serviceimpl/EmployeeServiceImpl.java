package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResouceNotFoundException;
import com.example.demo.model.Employee_info;
import com.example.demo.repository.EmployeeRepository;


import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

		private EmployeeRepository employeeRepository;
		
		public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
			super();
			this.employeeRepository = employeeRepository;
		}

		@Override
		public Employee_info saveEmployee(Employee_info employee_info) {
			return employeeRepository.save(employee_info);
		}

		@Override
		public List<Employee_info> getAllEmployees() {
			return employeeRepository.findAll();
		}

		@Override
		public Employee_info getEmployeeById(long id) {
			return employeeRepository.findById(id).orElseThrow(() -> 
							new ResouceNotFoundException("Employee not found"));
			
		}

		@Override
		public Employee_info updateEmployee(Employee_info employee_info, long id) {
			
			//  check whether employee with given id is exist in DB or not
			Employee_info existingEmployee = employeeRepository.findById(id).orElseThrow(
					() -> new ResouceNotFoundException("Employee not found")); 
			
			existingEmployee.setFirstName(employee_info.getFirstName());
			existingEmployee.setLastName(employee_info.getLastName());
			existingEmployee.setEmailId(employee_info.getEmailId());
			// save existing employee to DB
			employeeRepository.save(existingEmployee);
			return existingEmployee;
		}

		@Override
		public void deleteEmployee(long id) {
			
			// check whether a employee exist in a DB or not
			employeeRepository.findById(id).orElseThrow(() -> 
			new ResouceNotFoundException("Employee not found"));
	employeeRepository.deleteById(id);
		}
		
	
}
