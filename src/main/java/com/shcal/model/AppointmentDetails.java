package com.shcal.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shcal.formatter.CustomDateDeserializer;
import com.shcal.formatter.CustomDateSerializer;

public class AppointmentDetails implements Serializable {

	private static final long serialVersionUID = 1527870796694588164L;
	private int idAppointment;
	private String category;
	private Date date;
	private String name;
	private EmployeeDetails employee;
	
	public AppointmentDetails(){}
	
	public AppointmentDetails(Appointment app)
	{
		setIdAppointment(app.getIdAppointment());
		setCategory(app.getCategory());
		setName(app.getName());
		setDate(app.getDate());
		setEmployee(new EmployeeDetails(app.getEmployee()));
	}
	
	public int getIdAppointment() {
		return idAppointment;
	}
	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDate() {
		return date;
	}
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EmployeeDetails getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDetails employee) {
		this.employee = employee;
	}
	
	public Appointment convertAppointment()
	{
		Appointment app = new Appointment();
		app.setIdAppointment(getIdAppointment());
		app.setCategory(getCategory());
		app.setDate(getDate());
		app.setName(getName());
		return app;
	}

}
