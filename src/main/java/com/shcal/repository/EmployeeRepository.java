package com.shcal.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shcal.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findByFirstName(String firstName);
	public List<Employee> findByIdEmployeeIn(Collection<Integer> idEmployee);
	public Employee findByemail(String email);
	
}