package com.springboot.crudoperations.employees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.crudoperations.employees.entity.Employees;


public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

	@Query("Select e from Employees e where e.id=:id")
	Employees findById(int id);
	
	@Query("Select c from Employees c where c.city=:city")
	List<Employees> findByCity(String city);

	@Query("Select e.id from Employees e where e.city=:city")
	int findEmpidByCity(String city);
}

