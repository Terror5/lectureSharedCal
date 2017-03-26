package com.shcal.service;

import java.util.Collection;
import java.util.List;

import com.shcal.model.Employee;
import com.shcal.exception.EmployeeNotFound;


public interface EmployeeService {
        
        public Employee create(Employee employee);
        public Employee delete(Integer id) throws EmployeeNotFound;
        public List<Employee> findAll();
        public Employee update(Employee employee) throws EmployeeNotFound;
        public Employee findById(Integer id);
        
    	public List<Employee> findByFirstName(String firstName);
    	public List<Employee> findByIdEmployeeIn(Collection<Integer> idEmployees);
    	public Employee findByemail(String email);
		

}
