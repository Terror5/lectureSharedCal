package com.shcal.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shcal.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> 
{
	public List<Project> findByName(String name); 
	public List<Project> findByIdProjectIn(Collection<Integer> idProject);
	public List<Project> findByProjectLeader(String projectLeader);  
	public List<Project> findByDate(Date date);  
}
