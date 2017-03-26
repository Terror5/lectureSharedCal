package com.shcal.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.shcal.model.Project;
import com.shcal.exception.ProjectNotFound;

public interface ProjectService 
{
	public Project createProject(Project project);
    public Project delete(int id) throws ProjectNotFound;
    public List<Project> findAll();
    public Project update(Project project) throws ProjectNotFound;
    public Project findById(int id);
     
    public List<Project> findByName(String name); 
 	public List<Project> findByIdProjectIn(Collection<Integer> idProject);
 	public List<Project> findByProjectLeader(String leader);  
 	public List<Project> findByDate(Date date); 
}
