package com.shcal.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ProjectAssignement database table.
 * 
 */
@Embeddable
public class ProjectAssignementPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Project_idProject", insertable=false, updatable=false)
	private int project_idProject;

	@Column(name="Employee_idEmployee", insertable=false, updatable=false)
	private int employee_idEmployee;

	public ProjectAssignementPK() {
	}
	public int getProject_idProject() {
		return this.project_idProject;
	}
	public void setProject_idProject(int project_idProject) {
		this.project_idProject = project_idProject;
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
		if (!(other instanceof ProjectAssignementPK)) {
			return false;
		}
		ProjectAssignementPK castOther = (ProjectAssignementPK)other;
		return 
			(this.project_idProject == castOther.project_idProject)
			&& (this.employee_idEmployee == castOther.employee_idEmployee);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.project_idProject;
		hash = hash * prime + this.employee_idEmployee;
		
		return hash;
	}
}