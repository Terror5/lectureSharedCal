// FB
package com.shcal.service;

import java.sql.Date;
import java.util.List;

import com.shcal.exception.ProjectAssignementNotFound;
import com.shcal.model.Employee;
import com.shcal.model.Project;
import com.shcal.model.ProjectAssignement;

public interface ProjectAssignementService {
	
	public ProjectAssignement create(ProjectAssignement projectAssignement);
	public ProjectAssignement delete(ProjectAssignement projectAssignement) throws ProjectAssignementNotFound;
	public List<ProjectAssignement> findAll();
	public ProjectAssignement update(ProjectAssignement projectAssignement) throws ProjectAssignementNotFound;
	
	
	public ProjectAssignement findByCreatorId(String creatorId);
	public ProjectAssignement findByDate(Date date);
	public ProjectAssignement findByEmployee(Employee employeeId);
	public ProjectAssignement findByProject(Project projectId);

}
