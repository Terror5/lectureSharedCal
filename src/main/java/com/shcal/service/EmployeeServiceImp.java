package com.shcal.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shcal.exception.EmployeeNotFound;
import com.shcal.model.Employee;
import com.shcal.repository.EmployeeRepository;


@Service
public class EmployeeServiceImp implements EmployeeService {

	@Resource
	private EmployeeRepository employeeRepository;
	

	@Transactional
	public Employee create(Employee employee) {
		Employee createdEmployee = employee;
		if(createdEmployee.getEnterprise() != null && createdEmployee.getEnterprise().getIdEnterprise() == -1){
			createdEmployee.setEnterprise(null);
		}
		return employeeRepository.save(createdEmployee);
	}


	@Transactional(rollbackFor=EmployeeNotFound.class)
	public Employee delete(Integer id) throws EmployeeNotFound {
		Employee deletedEmployee = employeeRepository.findOne(id);
		
		if(deletedEmployee == null){
			throw new EmployeeNotFound();
		}
		
		employeeRepository.delete(deletedEmployee);
		return deletedEmployee;
	}


	@Transactional
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}


	@Transactional(rollbackFor=EmployeeNotFound.class)
	public Employee update(Employee employee) throws EmployeeNotFound {
		Employee updatedEmployee = employeeRepository.findOne(employee.getIdEmployee());
		
		if(updatedEmployee == null){
			throw new EmployeeNotFound();
		}
		
		updatedEmployee.setFirstName(employee.getFirstName());
		updatedEmployee.setLastName(employee.getLastName());
		updatedEmployee.setEmail(employee.getEmail());
		updatedEmployee.setActive(employee.getActive()); 
		if(!(employee.getPwHash() == null || "".equals(employee.getPwHash())))
			updatedEmployee.setPwHash(employee.getPwHash());
		updatedEmployee.setAddress(employee.getAddress());
		updatedEmployee.setPhone(employee.getPhone());
		updatedEmployee.setRole(employee.getRole());
		if(employee.getEnterprise() != null){
			if(employee.getEnterprise().getIdEnterprise() == -1){
				updatedEmployee.setEnterprise(null);
			} 
			else {
				updatedEmployee.setEnterprise(employee.getEnterprise());
			}
		}
		return updatedEmployee;
	}


	@Override
	public List<Employee> findByIdEmployeeIn(Collection<Integer> idEmployees) {
		return employeeRepository.findByIdEmployeeIn(idEmployees);
	}


	@Override
	public Employee findByemail(String email) {
		return employeeRepository.findByemail(email);
	}


	@Override
	public List<Employee> findByFirstName(String firstName) {
		return employeeRepository.findByFirstName(firstName);
	}


	@Override
	public Employee findById(Integer id) {
		return employeeRepository.findOne(id);
	}


	



}
