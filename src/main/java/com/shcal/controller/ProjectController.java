package com.shcal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shcal.exception.ProjectNotFound;
import com.shcal.model.Appointment;
import com.shcal.model.Project;
import com.shcal.service.ProjectService;
import com.shcal.validation.ProjectValidator;

@Controller
@RequestMapping(value="/admin/project")
public class ProjectController 
{
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectValidator projectValidator;

    
    @RequestMapping(value="/list", method=RequestMethod.GET)  
    public ModelAndView listProject() 
    {
        ModelAndView mav = new ModelAndView("admin/ProjectList");
        List<Project> projectList = projectService.findAll();
        mav.addObject("projectList", projectList);   	
        return mav;  
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)  
    public ModelAndView updateProject(@PathVariable Integer id) 
    {
        ModelAndView mav = new ModelAndView("admin/ProjectEdit");
        Project project = projectService.findById(id);
        mav.addObject("project", project);
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        mav.addObject("appointmentList", appointmentList);
        return mav; 
    }
    
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)  
    public ModelAndView updateProjectForm(@ModelAttribute("project") Project project,
            BindingResult result,
            @PathVariable Integer id,
            final RedirectAttributes redirectAttributes) 
    {
    	projectValidator.setUpdate(true);
    	projectValidator.validate(project, result);

        if (result.hasErrors())            		
                return new ModelAndView("admin/ProjectEdit","appointmentList",new ArrayList<Project>());                

        ModelAndView mav = new ModelAndView("redirect:/admin/project/list.html"); 
        String message = "Project  "+ project.getName() +"was successfully updated.";
       
		try 
		{
			projectService.update(project);
		} 
		catch (ProjectNotFound e) 
		{
			e.printStackTrace();
		}
		 
        redirectAttributes.addFlashAttribute("message", message);
        return mav;   
    }
    
    @RequestMapping(value="/create", method=RequestMethod.GET)  
    public ModelAndView insertProject() 
    { 
        ModelAndView mav = new ModelAndView("admin/ProjectNew", "project", new Project());
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        mav.addObject("appointmentList", appointmentList);
        return mav;
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)  
    public ModelAndView insertProjectForm(@ModelAttribute("project") Project project,
            BindingResult result,
            final RedirectAttributes redirectAttributes) 
    {
    	
    	projectValidator.setUpdate(false);
    	projectValidator.validate(project, result);
        
    	if (result.hasErrors())
        {          
            return new ModelAndView("admin/ProjectNew", "teamList", new ArrayList<Appointment>());
        }
        
        ModelAndView mav = new ModelAndView();
        String message = "New Project "+ project.getName() +" was successfully created.";
        
        projectService.createProject(project);
       
        mav.setViewName("redirect:/admin/project/list.html"); 
                        
        redirectAttributes.addFlashAttribute("message", message); 
        return mav; 
    }
    
    @SuppressWarnings("finally")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)  
    public ModelAndView deleteProject(@PathVariable Integer id,
    		final RedirectAttributes redirectAttributes) 
    {
        ModelAndView mav = new ModelAndView("redirect:/admin/project/list.html");
        String message = "";
        
        try 
        {
            Project project = projectService.delete(id);
            message = "The Project " + project.getIdProject() + "  " + project.getName() + " was successfully deleted.";           
            redirectAttributes.addFlashAttribute("message", message);
		} 
        catch (ProjectNotFound e) 
        {
			message = "The Project with id " + id + " could not be deleted - Rollback."; 
		}
        finally
        {
        	redirectAttributes.addFlashAttribute("message", message);
        	return mav;
        }   
    }
}
