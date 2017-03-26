package com.shcal.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shcal.service.EmployeeService;


@Controller
public class NavigationController {
	
    @Autowired
    private EmployeeService employeeService;
    


   	@RequestMapping(value="/secured/home", method=RequestMethod.GET)
   	public ModelAndView newHomePage(Principal principal){
   		ModelAndView mav = new ModelAndView("secured/home","winuser",principal.getName());
   		return mav;
   	}

    
   	@RequestMapping(value={"/secured/ProjectControl"}, method=RequestMethod.GET)
   	public ModelAndView newProjectControlPage()
   	{
   		return new ModelAndView("secured/ProjectControl");
   	}
   	       
   	
   	@RequestMapping(value="/admin/control", method=RequestMethod.GET)
   	public ModelAndView newAdminControlPage(){
   		
   		ModelAndView mav = new ModelAndView("admin/control");
   		return mav;
   	}     	                      
}


