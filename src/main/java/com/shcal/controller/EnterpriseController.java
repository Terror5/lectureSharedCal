//FB
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

import com.shcal.exception.EnterpriseNotFound;
import com.shcal.model.Enterprise;
import com.shcal.service.EnterpriseService;
import com.shcal.validation.EnterpriseValidator; 

@Controller
@RequestMapping(value="/admin/enterprise")
public class EnterpriseController {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private EnterpriseValidator enterpriseValidator;
	
	 @RequestMapping(value="/list", method=RequestMethod.GET)
	 public ModelAndView listEnterprises(){
		 ModelAndView mav = new ModelAndView("admin/EnterpriseList");
		 List<Enterprise> enterpriseList = enterpriseService.findAll(); 
		 mav.addObject("enterpriseList", enterpriseList);
		 return mav;
	 }
	 
	 @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)  
	    public ModelAndView updateEnterprise(@PathVariable Integer id) {
		 ModelAndView mav = new ModelAndView("admin/EnterpriseEdit");
	        Enterprise enterprise = enterpriseService.findById(id);
	        mav.addObject("enterprise", enterprise);
	        return mav;
	 }
	 
	 @SuppressWarnings("finally")
		@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)  
	    public ModelAndView updateEnterpriseForm(@ModelAttribute("enterprise") Enterprise enterprise,
	            BindingResult result,
	            @PathVariable Integer id,
	            final RedirectAttributes redirectAttributes) {
	    	
		
	    	
		 	enterpriseValidator.setUpdate(true);
	    	enterpriseValidator.validate(enterprise,result);

	    	 if (result.hasErrors())            		
	             return new ModelAndView("admin/EnterpriseEdit","enterpriseList",new ArrayList<Enterprise>());           

	        ModelAndView mav = new ModelAndView("redirect:/admin/enterprise/list.html"); 
	        String message = "Enterprise  "+ enterprise.getEmail() +"was successfully updated.";
	        try {
				enterpriseService.update(enterprise);
			} catch (EnterpriseNotFound e) {
				message = "Enterprise ("+ enterprise.getName() +" ) update failed - Rollback.";
			}
	        finally{
	        	redirectAttributes.addFlashAttribute("message", message);
	        	return mav;
	        }    
	    }
	 
	 @RequestMapping(value="/create", method=RequestMethod.GET)  
	    public ModelAndView insertEnterprise() { 
	        ModelAndView mav = new ModelAndView("admin/EnterpriseNew", "enterprise", new Enterprise());
	        return mav;
	    }
	    
	    @RequestMapping(value="/create", method=RequestMethod.POST)  
	    public ModelAndView insertEnterpriseForm(@ModelAttribute("enterprise") Enterprise enterprise,
	            BindingResult result,
	            final RedirectAttributes redirectAttributes) {
	    	
	    	enterpriseValidator.setUpdate(false);
	    	enterpriseValidator.validate(enterprise,result);
	        if (result.hasErrors()){          
	            return new ModelAndView("admin/EnterpriseNew","teamList",new ArrayList<Enterprise>());//enterpriseService.findAll();
	        }
	        
	        ModelAndView mav = new ModelAndView();
	        String message = "New Enterprise "+enterprise.getName()+" was successfully created.";
	        
	        enterpriseService.create(enterprise);
	       
	        mav.setViewName("redirect:/admin/enterprise/list.html"); 
	                        
	        redirectAttributes.addFlashAttribute("message", message); 
	        return mav; 
	    }
	    
	    @SuppressWarnings("finally")
		@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)  
	    public ModelAndView deleteEnterprise(@PathVariable Integer id,
	    		final RedirectAttributes redirectAttributes) {
	        ModelAndView mav = new ModelAndView("redirect:/admin/enterprise/list.html");
	        String message = "";
	        try {
	            Enterprise enterprise = enterpriseService.delete(id);
	            message = "The enterprise "+enterprise.getIdEnterprise()+" was successfully deleted.";           
	            redirectAttributes.addFlashAttribute("message", message);
			} catch (EnterpriseNotFound e) {
				message = "The Enterprise with id " + id + " could not be deleted - Rollback."; 
			}
	        finally{
	        	redirectAttributes.addFlashAttribute("message", message);
	        	return mav;
	        }   
	    }

}
