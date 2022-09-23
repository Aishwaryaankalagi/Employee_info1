package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee_info;
import com.example.demo.service.EmployeeService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")

public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	// build create employee REST API
	@PostMapping()
	public ResponseEntity<Employee_info> saveEmployee(@RequestBody Employee_info employee_info){
		return new ResponseEntity<Employee_info>(employeeService.saveEmployee(employee_info), HttpStatus.CREATED);
	}
	
	// build get all employees REST API
	@GetMapping
	public List<Employee_info> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	// build get employee by id REST API
	
	@GetMapping("{id}")
	public ResponseEntity<Employee_info> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee_info>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}
	
	// build update employee REST API

	@PutMapping("{id}")
	public ResponseEntity<Employee_info> updateEmployee(@PathVariable("id") long id
												  ,@RequestBody Employee_info employee_info){
		return new ResponseEntity<Employee_info>(employeeService.updateEmployee(employee_info, id), HttpStatus.OK);
	}
	
	// build delete employee REST API
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		
		// delete employee from DB
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}
	
}







	
	