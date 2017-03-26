package com.shcal.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.shcal.model.Project;

@Component
public class ProjectFormatter implements Formatter<Project> 
{
	@Override
	public String print(Project project, Locale locale) 
	{
		return String.valueOf(project.getIdProject()); 
	}

	@Override
	public Project parse(String formatted, Locale locale) throws ParseException 
	{
		return new Project();
	}
}
