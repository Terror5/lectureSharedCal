package com.shcal.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shcal.model.Forgot;
import com.shcal.service.EmployeeService;

@Component
public class ForgotValidator implements Validator {
    
    @Autowired
    private EmployeeService employeeService;

    public boolean supports(Class<?> clazz) {
            return Forgot.class.isAssignableFrom(clazz);
    }

    
    public void validate(Object target, Errors errors) {
    	
    		Forgot forgot = (Forgot) target;
    		String eMail = forgot.getEmail();
    		ValidationUtils.rejectIfEmpty(errors, "email", "user.eMail.empty");
    		
            if(eMail != null){
		        if(employeeService.findByemail(eMail) == null){
		        	errors.rejectValue("email", "user.not");
		        }
            }
                       
    }

}
