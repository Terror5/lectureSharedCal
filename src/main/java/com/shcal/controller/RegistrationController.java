
package com.shcal.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.shcal.model.Forgot;
import com.shcal.model.Password;
import com.shcal.model.Employee;
import com.shcal.service.CustomUrlService;
import com.shcal.service.EmailService;
import com.shcal.service.EmployeeService;
import com.shcal.validation.ForgotValidator;
import com.shcal.validation.RegistrationValidator;

@Controller
@RequestMapping(value="/public")
public class RegistrationController {

	@Autowired
    private EmployeeService employeeService;   
    
    @Autowired
    private RegistrationValidator passwordValidator;       
  
    @Autowired
    private CustomUrlService customUrlService;
    
    @Autowired
    private ForgotValidator forgotValidator;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PasswordEncoder encoder;
    
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public ModelAndView newUserPage() {
            ModelAndView mav = new ModelAndView("public/register", "password", new Password());
            return mav;
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute("password") Password password,
                    BindingResult result,
                    final RedirectAttributes redirectAttributes,
                    HttpServletRequest request) {
    	
    		passwordValidator.validate(password, result);
    		
            if (result.hasErrors()){               	
                return new ModelAndView("public/register");                
            }
            
            Employee employee = password.getEmployee();
            String email = employee.getEmail();
            ModelAndView mav = new ModelAndView();
            String message = "New User "+email+" was successfully created.";
                     
            StringBuffer url = request.getRequestURL();
            url.replace(url.indexOf("public"), url.length(), "public/activate/"+email+"/hash/"+encoder.encode(email));  
            
            emailService.sendActivationMail(employee,url.toString());
            
            employee.setPwHash(encoder.encode(password.getPwString1()));
            employee.setRole("USER");
            employeeService.create(employee);            	
           
            mav.setViewName("redirect:/secured/home"); 
                            
            redirectAttributes.addFlashAttribute("message", message);        
            return mav;                
    }    
    
    
    @RequestMapping(value="/activate/{email}/hash/{hash}", method=RequestMethod.GET)
    public ModelAndView activateUserPage(@PathVariable String email,
    		@PathVariable String hash,
    		HttpServletRequest request) throws EmployeeNotFound{
    	
    	ModelAndView mav = new ModelAndView("public/activate");
    	
    	Employee employee = employeeService.findByemail(email);
    	String message = "";

    	if(encoder.matches(email, hash)){
    		message = "Welcome " + employee.getFullName() + "! Your account was successfully activated.";
    		employee.setActive(true);
    		employeeService.update(employee);
    	} else {
    		message = "Wrong activation URL. Trying to send new activation E-Mail!";
    		if(email != null){
    			
                StringBuffer url = request.getRequestURL();
                url.replace(url.indexOf("public"), url.length(), "public/activate/"+email+"/hash/"+encoder.encode(email));    			
    			emailService.sendActivationMail(employee,url.toString());
    		}
    	}    	   	
    	
    	mav.addObject("message",message);
    	mav.addObject("title","Activation");
    	return mav;
    }
    
    @RequestMapping(value="/forgot", method=RequestMethod.GET)
    public ModelAndView forgotUserPage(){
    	ModelAndView mav = new ModelAndView("public/forgot");
    	mav.addObject("forgot", new Forgot());
    	mav.addObject("title","Password forgotten?");
    	return mav;
    }
   
    @RequestMapping(value="/forgot", method=RequestMethod.POST)
    public ModelAndView forgotUserForm(@ModelAttribute("forgot") Forgot forgot,
    									BindingResult result,
    									RedirectAttributes redirect){
    	forgotValidator.validate(forgot, result);
    	if(result.hasErrors()){
    		return new ModelAndView("public/forgot");
    	}
    	
    	Employee employee = employeeService.findByemail(forgot.getEmail());
    	String password = newPassword(8);
    	employee.setPwHash(encoder.encode(password));
    	try {
    		employeeService.update(employee);
		} catch (Exception e) {
			return new ModelAndView("public/forgot");
		}
    
    	emailService.sendPasswordMail(employee, password);
    	
    	ModelAndView mav = new ModelAndView("redirect:/secured/home");
    	return mav;
    	
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
