package com.shcal.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.List;


/**
 * The persistent class for the Employee database table.
 * 
 */
@Entity
@Table(name="employee")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idEmployee;

	@Column(name="Address")
	private String address;

	@Column(name="Email")
	private String email;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="LastName")
	private String lastName;

	@Column(name="Phone")
	private String phone;

	@Column(name="Role")
	private String role;
	
	private String pwHash;
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active;

	//bi-directional many-to-one association to Affiliation
	@OneToMany(mappedBy="employee")
	private List<Affiliation> affiliations;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="employee")
	private List<Appointment> appointments;

	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	@JoinColumn(name="Enterprise_idEnterprise")
	private Enterprise enterprise;

	//bi-directional many-to-one association to ProjectAssignement
	@OneToMany(mappedBy="employee")
	private List<ProjectAssignement> projectAssignements;

	public Employee() {
	}
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
	public Employee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public int getIdEmployee() {
		return this.idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPwHash() {
		return pwHash;
	}

	public void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Affiliation> getAffiliations() {
		return this.affiliations;
	}

	public void setAffiliations(List<Affiliation> affiliations) {
		this.affiliations = affiliations;
	}

	public Affiliation addAffiliation(Affiliation affiliation) {
		getAffiliations().add(affiliation);
		affiliation.setEmployee(this);

		return affiliation;
	}

	public Affiliation removeAffiliation(Affiliation affiliation) {
		getAffiliations().remove(affiliation);
		affiliation.setEmployee(null);

		return affiliation;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setEmployee(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setEmployee(null);

		return appointment;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public List<ProjectAssignement> getProjectAssignements() {
		return this.projectAssignements;
	}

	public void setProjectAssignements(List<ProjectAssignement> projectAssignements) {
		this.projectAssignements = projectAssignements;
	}

	public ProjectAssignement addProjectAssignement(ProjectAssignement projectAssignement) {
		getProjectAssignements().add(projectAssignement);
		projectAssignement.setEmployee(this);

		return projectAssignement;
	}

	public ProjectAssignement removeProjectAssignement(ProjectAssignement projectAssignement) {
		getProjectAssignements().remove(projectAssignement);
		projectAssignement.setEmployee(null);

		return projectAssignement;
	}

}