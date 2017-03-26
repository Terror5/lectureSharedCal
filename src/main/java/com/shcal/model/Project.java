package com.shcal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProject;

	@Temporal(TemporalType.DATE)
	@Column(name="Date")
	private Date date;

	@Column(name="Name")
	private String name;

	@Column(name="ProjectLeader")
	private String projectLeader;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="project")
	private List<Appointment> appointments;

	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	@JoinColumn(name="Enterprise_idEnterprise")
	private Enterprise enterprise;

	//bi-directional many-to-one association to ProjectAssignement
	@OneToMany(mappedBy="project")
	private List<ProjectAssignement> projectAssignements;

	public Project() {
	}

	public int getIdProject() {
		return this.idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
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

	public String getProjectLeader() {
		return this.projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setProject(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setProject(null);

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
		projectAssignement.setProject(this);

		return projectAssignement;
	}

	public ProjectAssignement removeProjectAssignement(ProjectAssignement projectAssignement) {
		getProjectAssignements().remove(projectAssignement);
		projectAssignement.setProject(null);

		return projectAssignement;
	}

}