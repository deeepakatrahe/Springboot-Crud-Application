 package com.springboot.crudoperations.employees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.crudoperations.employees.entity.Transactions;

public interface TransactionsRepository extends JpaRepository <Transactions, Integer>{

	//@Query(value="select * from Transactions order by salary ASC", nativeQuery=true)
	List<Transactions> findByEmpidOrderBySalaryDesc(int empid);
	
	@Query("Select e.id from Employees e INNER JOIN Transactions t on e.id=t.empid")
	int getQuery();
	
}
