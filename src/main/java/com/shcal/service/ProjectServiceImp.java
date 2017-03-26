package com.shcal.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shcal.exception.ProjectNotFound;
import com.shcal.model.Project;
import com.shcal.repository.ProjectRepository;

@Service
public class ProjectServiceImp implements ProjectService
{
	@Resource
	private ProjectRepository projectRepository;
	
	@Transactional
	public Project createProject(Project project) 
	{
		Project createProject = project;
		
//		if(createProject.getAppointments() != null)
//		{
//			createProject.setAppointments(null);  
//		}
//		TODO 
//		if(createProject.getEnterprise() != null)
//		{
//			createProject.setEnterprise(null);   
//		}
		
		return projectRepository.save(createProject); 
	}

	@Transactional(rollbackFor = ProjectNotFound.class)
	public Project delete(int id) throws ProjectNotFound 
	{
		Project deleteProject = projectRepository.findOne(id);
		
		if(deleteProject == null)
		{
			throw new ProjectNotFound();
		}
		
		projectRepository.delete(deleteProject);
		return deleteProject;
	}

	@Transactional
	public List<Project> findAll() 
	{
		return projectRepository.findAll();
	}

	@Transactional(rollbackFor = ProjectNotFound.class)
	public Project update(Project project) throws ProjectNotFound 
	{
		Project updateProject = projectRepository.findOne(project.getIdProject());
		
		if(updateProject == null)
		{
			throw new ProjectNotFound(); 
		}
		
		updateProject.setName(project.getName());
		updateProject.setDate(project.getDate()); 
		updateProject.setProjectLeader(project.getProjectLeader());
		updateProject.setAppointments(project.getAppointments());
		updateProject.setEnterprise(updateProject.getEnterprise());
		updateProject.setProjectAssignements(updateProject.getProjectAssignements()); 
		
		
//		if(project.getAppointments() != null)
//		{
//			updateProject.setAppointments(null);
//		}
//		else 
//		{
//			updateProject.setAppointments(updateProject.getAppointments()); 
//		}
//		TODO
//		if(project.getEnterprise() != null)
//		{
//			updateProject.setEnterprise(null);
//		}
//		else 
//		{
//			updateProject.setEnterprise(updateProject.getEnterprise());   
//		}
		
		return updateProject;
	}

	@Override
	public Project findById(int id) 
	{
		return projectRepository.findOne(id);  

	}

	@Override
	public List<Project> findByName(String name) 
	{
		return projectRepository.findByName(name);  
	}

	@Override
	public List<Project> findByIdProjectIn(Collection<Integer> idProject) 
	{
		return projectRepository.findByIdProjectIn(idProject); 
	}

	@Override
	public List<Project> findByProjectLeader(String leader) 
	{
		return projectRepository.findByProjectLeader(leader);
	}

	@Override
	public List<Project> findByDate(Date date) 
	{
		return projectRepository.findByDate(date);
	}

}
