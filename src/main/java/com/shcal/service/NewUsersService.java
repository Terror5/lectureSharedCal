package com.shcal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shcal.model.Employee;

@Service
public class NewUsersService {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	private String[] names = new String[]{"test","funger","stefan","maike","user"};
	
	public void createAdminAccount(){
		if(employeeService.findByemail("admin@admin.de") == null){
			createUser("admin","ADMIN");
		}
	}
	
	public int createUsers(){
		int i = 0;
		String roleString = "USER";
		
		for(String name : names){
			
			if(name.equals("funger")){
				roleString = "ADMIN";
			}
			else{
				roleString = "USER";
			}
			if(employeeService.findByemail(nameToMail(name)) != null){
				try {				
					createUser(name,roleString);
					i += 1;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return i;
	}
	
	public void setTestNames(String[] testNames){
		names = testNames;
	}
	
	
	private void createUser(String name, String roleID){		
    	Employee user = new Employee();
		user.setFirstName(name);
		user.setLastName(name);
		user.setEmail(nameToMail(name));
		user.setActive(true);
		user.setPwHash(encoder.encode(name));
		user.setAddress(name+"weg 12");
		user.setRole(roleID);
		user.setPhone(String.valueOf(name.hashCode()));
		employeeService.create(user);			
	}
    
    private String nameToMail(String name)
    {
    	return name + "@" + name + ".de";
    }
}
