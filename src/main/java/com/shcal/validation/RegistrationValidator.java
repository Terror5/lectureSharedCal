package com.shcal.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shcal.model.Employee;
import com.shcal.model.Password;
import com.shcal.service.EmployeeService;


@Component
public class RegistrationValidator implements Validator {
       
    @Autowired
    private EmployeeService employeeService;

    public boolean supports(Class<?> clazz) {
        return Password.class.isAssignableFrom(clazz);
}


public void validate(Object target, Errors errors) {
	
		Password password = (Password) target;
		String pwString1 = password.getPwString1();
		String pwString2 = password.getPwString2();   		
		
		ValidationUtils.rejectIfEmpty(errors, "pwString1", "password.empty");
		ValidationUtils.rejectIfEmpty(errors, "pwString2", "password.empty");
		
		if(!pwString1.equals(pwString2)){
			errors.rejectValue("pwString1", "password.notequal");
		}
		
        Employee employee = password.getEmployee();
        
        String eMail = employee.getEmail();
                   
        ValidationUtils.rejectIfEmpty(errors, "employee.firstName", "user.firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "employee.lastName", "user.lastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "employee.email", "user.eMail.empty");
        
        if (eMail != null){
        	if(eMail.indexOf("@") == -1){
        		errors.rejectValue("employee.email", "user.eMail.containsNoAt");
        	}
        }
        
        if(eMail != null){
	        if(employeeService.findByemail(eMail) != null){
	        	errors.rejectValue("user.idUser", "user.eMail.existing");
	        }
        }
                   
}

}

