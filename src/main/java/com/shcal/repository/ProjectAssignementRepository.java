package com.shcal.repository;

import com.shcal.model.Project;
import com.shcal.model.Employee;
import com.shcal.model.ProjectAssignement;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;

public  interface ProjectAssignementRepository extends JpaRepository<ProjectAssignement, Integer>{
	
	public ProjectAssignement findByCreatorId(String creatorId);
	public ProjectAssignement findByDate(Date date);
	public ProjectAssignement findByEmployee(Employee employeeId);
	public ProjectAssignement findByProject(Project projectId);

	
}
