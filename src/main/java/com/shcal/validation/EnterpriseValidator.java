//FB
package com.shcal.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shcal.model.Employee;
import com.shcal.model.Enterprise;
import com.shcal.service.EnterpriseService;

@Component
public class EnterpriseValidator implements Validator {

	   @Autowired
	    private EnterpriseService enterpriseService;

	    private boolean update = false;
	    
	    public boolean supports(Class<?> clazz) {
	        return Employee.class.isAssignableFrom(clazz);
	    }
	    
	    public void validate(Object target, Errors errors) {
	    	
	    	Enterprise enterprise = (Enterprise) target;
	    	ValidationUtils.rejectIfEmpty(errors, "name", "enterprise.name.empty");
	        ValidationUtils.rejectIfEmpty(errors, "contactPerson", "enterprise.contactPerson.empty");
	        ValidationUtils.rejectIfEmpty(errors, "address", "enterprise.address.empty");
	        ValidationUtils.rejectIfEmpty(errors, "email", "enterprise.email.empty");
	        
	        String eMail = enterprise.getEmail();
	        
	        if (eMail != null){
	        	if(eMail.indexOf("@") == -1){
	        		errors.rejectValue("email", "enterprise.email.containsNoAt");
	        	}
	        }
	        
	        if(eMail != null && !update){
		        if(enterpriseService.findByEmail(eMail) != null){
		        	errors.rejectValue("email", "enterprise.email.existing");
		        }
	        }
	    }
	    
	    public void setUpdate(boolean update){
	    	this.update = update;
	    }
}
