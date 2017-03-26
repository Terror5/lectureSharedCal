package com.shcal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Appointment database table.
 * 
 */
@Entity
@NamedQuery(name="Appointment.findAll", query="SELECT a FROM Appointment a")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAppointment;

	@Column(name="Category")
	private String category;

	@Temporal(TemporalType.DATE)
	@Column(name="Date")
	private Date date;

	@Column(name="Name")
	private String name;

	//bi-directional many-to-one association to Affiliation
	@OneToMany(mappedBy="appointment")
	private List<Affiliation> affiliations;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="idProject")
	private Project project;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="idEmployee")
	private Employee employee;

	public Appointment() {
	}

	public int getIdAppointment() {
		return this.idAppointment;
	}

	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Affiliation> getAffiliations() {
		return this.affiliations;
	}

	public void setAffiliations(List<Affiliation> affiliations) {
		this.affiliations = affiliations;
	}

	public Affiliation addAffiliation(Affiliation affiliation) {
		getAffiliations().add(affiliation);
		affiliation.setAppointment(this);

		return affiliation;
	}

	public Affiliation removeAffiliation(Affiliation affiliation) {
		getAffiliations().remove(affiliation);
		affiliation.setAppointment(null);

		return affiliation;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}