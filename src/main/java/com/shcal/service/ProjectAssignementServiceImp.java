package com.shcal.service;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shcal.exception.ProjectAssignementNotFound;
import com.shcal.model.Employee;
import com.shcal.model.Project;
import com.shcal.model.ProjectAssignement;
import com.shcal.repository.ProjectAssignementRepository;

@Service
public class ProjectAssignementServiceImp implements ProjectAssignementService{
	
	@Resource
	private ProjectAssignementRepository projectAssignementRepository;
	
	@Transactional
	public ProjectAssignement create(ProjectAssignement projectAssignement){
		ProjectAssignement createdProjectAssignement = projectAssignement;
		return projectAssignementRepository.save(createdProjectAssignement);
	}
	@Transactional(rollbackFor=ProjectAssignementNotFound.class)
	public ProjectAssignement delete(ProjectAssignement projectAssignement) throws ProjectAssignementNotFound {
		ProjectAssignement deletedProjectAssignement = projectAssignement;
		
		if(deletedProjectAssignement == null){
			throw new ProjectAssignementNotFound();
		}
		projectAssignementRepository.delete(deletedProjectAssignement);
		return deletedProjectAssignement;
	}
	@Transactional
	public List<ProjectAssignement> findAll(){
		return projectAssignementRepository.findAll();
	}
	
	// Set id??
	public ProjectAssignement update(ProjectAssignement projectAssignement) throws ProjectAssignementNotFound{
		
		ProjectAssignement updatedAssignement = projectAssignement;
		if(updatedAssignement == null){
			throw new ProjectAssignementNotFound();
		}
		updatedAssignement.setDate(updatedAssignement.getDate()); 
		updatedAssignement.setCreatorId(updatedAssignement.getCreatorId());
		updatedAssignement.setEmployee(updatedAssignement.getEmployee());
		updatedAssignement.setProject(updatedAssignement.getProject());
		return updatedAssignement;
	}
	
	public ProjectAssignement findByCreatorId(String creatorId){
		return projectAssignementRepository.findByCreatorId(creatorId);
	}
	public ProjectAssignement findByDate(Date date){
		return projectAssignementRepository.findByDate(date);
	}
	
	public ProjectAssignement findByEmployee(Employee employeeId){
		return projectAssignementRepository.findByEmployee(employeeId);
	} 
	public ProjectAssignement findByProject(Project projectId){
		return projectAssignementRepository.findByProject(projectId);
	}
}
