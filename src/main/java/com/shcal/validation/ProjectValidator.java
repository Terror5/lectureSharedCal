package com.shcal.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.shcal.model.Project;
import com.shcal.service.ProjectService;

@Component
public class ProjectValidator 
{
	 @Autowired
	    private ProjectService projectService;

	    private boolean update = false;
	    
	    public boolean supports(Class<?> clazz)
	    {
	        return Project.class.isAssignableFrom(clazz);
	    }


	public void validate(Object target, Errors errors) 
	{
		Project project = (Project) target;
		String name = project.getName();
	                   
		ValidationUtils.rejectIfEmpty(errors, "name", "project.name.empty");
	    ValidationUtils.rejectIfEmpty(errors, "date", "project.date.empty");
	    ValidationUtils.rejectIfEmpty(errors, "projectLeader", "project.projectLeader.empty");
	        
	    if(name != null && !update)
	    {
	    	if(projectService.findByName(name) != null)
	    	{
	    		errors.rejectValue("name", "project.name.existing");
	    	}
	    }    
	 }

	public void setUpdate(boolean update)
	{
		this.update = update;
	}
}
