package com.shcal.model;

import java.io.Serializable;

public class EmployeeDetails implements Serializable {

	private static final long serialVersionUID = 7629490885889913120L;
	private int idEmployee;
	private String firstName;
	private String lastName;
	
	public EmployeeDetails(){}
	
	public EmployeeDetails(Employee emp)
	{
		setIdEmployee(emp.getIdEmployee());
		setFirstName(emp.getFirstName());
		setLastName(emp.getLastName());
	}
	
	
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
