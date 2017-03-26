package com.shcal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ProjectAssignement database table.
 * 
 */
@Entity
@NamedQuery(name="ProjectAssignement.findAll", query="SELECT p FROM ProjectAssignement p")
public class ProjectAssignement implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProjectAssignementPK id;

	@Column(name="CreatorId")
	private String creatorId;

	@Temporal(TemporalType.DATE)
	@Column(name="Date")
	private Date date;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="Project_idProject", insertable=false, updatable=false)
	private Project project;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="Employee_idEmployee", insertable=false, updatable=false)
	private Employee employee;

	public ProjectAssignement() {
	}

	public ProjectAssignementPK getId() {
		return this.id;
	}

	public void setId(ProjectAssignementPK id) {
		this.id = id;
	}

	public String getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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