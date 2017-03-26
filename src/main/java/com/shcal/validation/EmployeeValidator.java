package com.shcal.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shcal.model.Employee;
import com.shcal.service.EmployeeService;


@Component
public class EmployeeValidator implements Validator {
       
    @Autowired
    private EmployeeService employeeService;

    private boolean update = false;
    
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }


public void validate(Object target, Errors errors) {

        Employee employee = (Employee) target;
        
        String eMail = employee.getEmail();
        String role = employee.getRole();
                   
        ValidationUtils.rejectIfEmpty(errors, "firstName", "user.firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "user.lastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.eMail.empty");
        ValidationUtils.rejectIfEmpty(errors, "role", "user.role.empty");
        
        if (eMail != null){
        	if(eMail.indexOf("@") == -1){
        		errors.rejectValue("email", "user.eMail.containsNoAt");
        	}
        }
        
        if(eMail != null && !update){
	        if(employeeService.findByemail(eMail) != null){
	        	errors.rejectValue("email", "user.eMail.existing");
	        }
        }
        
        if(role != null)
        {
        	if(!("ADMIN".equals(role) || ("USER".equals(role))))
        			errors.rejectValue("role", "user.role.wrong");
        }
	}

public void setUpdate(boolean update){
	this.update = update;
}

}

