package com.shcal.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shcal.exception.EmployeeNotFound;
import com.shcal.model.Employee;
import com.shcal.model.Enterprise;
import com.shcal.service.EmailService;
import com.shcal.service.EmployeeService;
import com.shcal.validation.EmployeeValidator;


@Controller
@RequestMapping(value="/admin/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeValidator employeeValidator;
	
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private EmailService emailService;
    
	//@Autowired
	//private EnterpriseService enterpriseService;
    
    @RequestMapping(value="/list", method=RequestMethod.GET)  
    public ModelAndView listEmployees() {
        ModelAndView mav = new ModelAndView("admin/EmployeeList");
        List<Employee> employeeList = employeeService.findAll();
        mav.addObject("employeeList", employeeList);   	
        return mav;  
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)  
    public ModelAndView updateEmployees(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("admin/EmployeeEdit");
        Employee employee = employeeService.findById(id);
        mav.addObject("employee", employee);
        List<Enterprise> enterpriseList = new ArrayList<Enterprise>();//enterpriseService.findAll();
        mav.addObject("enterpriseList", enterpriseList);
        return mav; 
    }
    
    @SuppressWarnings("finally")
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)  
    public ModelAndView updateEmployeesForm(@ModelAttribute("employee") Employee employee,
            BindingResult result,
            @PathVariable Integer id,
            final RedirectAttributes redirectAttributes) {
    	
    	employeeValidator.setUpdate(true);
    	employeeValidator.validate(employee,result);

        if (result.hasErrors())            		
                return new ModelAndView("admin/EmployeeEdit","enterpriseList",new ArrayList<Enterprise>());//enterpriseService.findAll();                

        ModelAndView mav = new ModelAndView("redirect:/admin/employee/list.html"); 
        String message = "Employee  "+ employee.getEmail() +"was successfully updated.";
        String pwd = employee.getPwHash();
    	if(!(pwd == null || "".equals(pwd)))
    		employee.setPwHash(encoder.encode(pwd));
        try {
			employeeService.update(employee);
			if(!(pwd == null || "".equals(pwd)))
				emailService.sendPasswordMail(employee, pwd);
		} catch (EmployeeNotFound e) {
			message = "Employee  "+ employee.getEmail() +" update failed - Rollback.";
		}
        finally{
        	redirectAttributes.addFlashAttribute("message", message);
        	return mav;
        }    
    }
    
    @RequestMapping(value="/create", method=RequestMethod.GET)  
    public ModelAndView insertEmployees() { 
        ModelAndView mav = new ModelAndView("admin/EmployeeNew", "employee", new Employee());
        List<Enterprise> enterpriseList = new ArrayList<Enterprise>();//enterpriseService.findAll();
        mav.addObject("enterpriseList", enterpriseList);
        return mav;
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)  
    public ModelAndView insertEmployeesForm(@ModelAttribute("employee") Employee employee,
            BindingResult result,
            final RedirectAttributes redirectAttributes) {
    	
    	employeeValidator.setUpdate(false);
    	employeeValidator.validate(employee,result);
        if (result.hasErrors()){          
            return new ModelAndView("admin/EmployeeNew","teamList",new ArrayList<Enterprise>());//enterpriseService.findAll();
        }
        
        ModelAndView mav = new ModelAndView();
        String message = "New Employee "+employee.getEmail()+" was successfully created.";
        
        employee.setPwHash(encoder.encode(employee.getPwHash()));
        employeeService.create(employee);
       
        mav.setViewName("redirect:/admin/employee/list.html"); 
                        
        redirectAttributes.addFlashAttribute("message", message); 
        return mav; 
    }
    
    @SuppressWarnings("finally")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)  
    public ModelAndView deleteEmployees(@PathVariable Integer id,
    		final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/admin/employee/list.html");
        String message = "";
        try {
            Employee employee = employeeService.delete(id);
            message = "The Employee "+employee.getIdEmployee()+"  "+employee.getEmail()+" was successfully deleted.";           
            redirectAttributes.addFlashAttribute("message", message);
		} catch (EmployeeNotFound e) {
			message = "The Employee with id " + id + " could not be deleted - Rollback."; 
		}
        finally{
        	redirectAttributes.addFlashAttribute("message", message);
        	return mav;
        }   
    }
    
    public String newPassword(int length){
    	String pw = "";
    	String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	for(int i = 1; i <= length; i++){	
    		int index = (int) (Math.random()*chars.length());
    		pw += chars.charAt(index);
    	}   	
    	return pw;
    }
}  