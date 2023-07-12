package com.springboot.crudoperations.employees.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crudoperations.employees.entity.Employees;
import com.springboot.crudoperations.employees.service.EmployeesService;

@RestController
public class EmployeesController {
	
	@Autowired
	private EmployeesService employeeService;
	
	org.jboss.logging.Logger logger = LoggerFactory.logger(EmployeesController.class);
	Logger log = LogManager.getLogger(EmployeesController.class);
	
	@RequestMapping("/employees")
	public List<Employees> getAllEmployees(){
		logger.info("Hey I am a log");
		log.info("(Controller)Here is list of your employeess:- " +employeeService.getAllEmployees());
		return employeeService.getAllEmployees();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/employees/{id}")
	public Employees getEmployee(@PathVariable int id){
		logger.info("Hey I am Log 2");
		log.info("(Controller) Here is you employee based on employee id :- "+employeeService.getEmployee(id));
		return employeeService.getEmployee(id);
	}
	
	@PostMapping("/employees")  
	public Employees saveEmployee(@RequestBody Employees employee)   
	{  
		logger.info("Hey I am Log 3");
		log.info("(Controller) Adding employee into DB:- " + employee);
		employeeService.saveOrUpdate(employee);  
		return employee;  
	}  
	
	@RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}")
	public Employees updateEmployee(@RequestBody Employees employee) {
		logger.info("Hey I am Log 4");
		log.info("(Controller) Employee updated :- "+ employee);
		
		employeeService.updateEmployee(employee);
		return employee; 
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}")
	public String deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
		return ("Deleted Employee with ID:- " + id); //returning id just to show on postman output - earlier return type was null
	}
	
	@GetMapping("/employees/getCity/{city}")
	public List<Employees> getEmployeeByCity(@PathVariable String city) {
		List<Employees> employee = employeeService.getEmployeebyCity(city);
		return employee;
	}
	
	@GetMapping("/employees/getEmpidByCity/{city}")
	public int getEmpidByCity(@PathVariable String city) {
		return employeeService.getIdByCity(city);		
	}
}	