package com.shcal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Affiliation database table.
 * 
 */
@Entity
@NamedQuery(name="Affiliation.findAll", query="SELECT a FROM Affiliation a")
public class Affiliation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AffiliationPK id;

	@Column(name="Creator")
	private String creator;

	@Temporal(TemporalType.DATE)
	@Column(name="Date")
	private Date date;

	//bi-directional many-to-one association to Appointment
	@ManyToOne
	@JoinColumn(name="Appointment_idAppointment", insertable=false, updatable=false)
	private Appointment appointment;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="Employee_idEmployee", insertable=false, updatable=false)
	private Employee employee;

	public Affiliation() {
	}

	public AffiliationPK getId() {
		return this.id;
	}

	public void setId(AffiliationPK id) {
		this.id = id;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Appointment getAppointment() {
		return this.appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}