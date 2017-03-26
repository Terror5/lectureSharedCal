package com.shcal.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Affiliation database table.
 * 
 */
@Embeddable
public class AffiliationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Appointment_idAppointment", insertable=false, updatable=false)
	private int appointment_idAppointment;

	@Column(name="Employee_idEmployee", insertable=false, updatable=false)
	private int employee_idEmployee;

	public AffiliationPK() {
	}
	public int getAppointment_idAppointment() {
		return this.appointment_idAppointment;
	}
	public void setAppointment_idAppointment(int appointment_idAppointment) {
		this.appointment_idAppointment = appointment_idAppointment;
	}
	public int getEmployee_idEmployee() {
		return this.employee_idEmployee;
	}
	public void setEmployee_idEmployee(int employee_idEmployee) {
		this.employee_idEmployee = employee_idEmployee;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AffiliationPK)) {
			return false;
		}
		AffiliationPK castOther = (AffiliationPK)other;
		return 
			(this.appointment_idAppointment == castOther.appointment_idAppointment)
			&& (this.employee_idEmployee == castOther.employee_idEmployee);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.appointment_idAppointment;
		hash = hash * prime + this.employee_idEmployee;
		
		return hash;
	}
}