package com.shcal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Enterprise database table.
 * 
 */
@Entity
@NamedQuery(name="Enterprise.findAll", query="SELECT e FROM Enterprise e")
public class Enterprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idEnterprise;

	@Column(name="Address")
	private String address;

	@Column(name="ContactPerson")
	private String contactPerson;

	@Column(name="Email")
	private String email;

	@Column(name="Name")
	private String name;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="enterprise")
	private List<Employee> employees;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="enterprise")
	private List<Project> projects;

	public Enterprise() {
	}

	public int getIdEnterprise() {
		return this.idEnterprise;
	}

	public void setIdEnterprise(int idEnterprise) {
		this.idEnterprise = idEnterprise;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setEnterprise(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setEnterprise(null);

		return employee;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setEnterprise(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setEnterprise(null);

		return project;
	}

}