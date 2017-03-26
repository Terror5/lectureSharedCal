package com.shcal.service;

import java.util.ArrayList;  
import java.util.Collection;  
import java.util.List;  
  


import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.security.core.GrantedAuthority;  
import org.springframework.security.core.authority.SimpleGrantedAuthority;  
import org.springframework.security.core.userdetails.User;  
import org.springframework.security.core.userdetails.UserDetails;  
import org.springframework.security.core.userdetails.UserDetailsService;  
import org.springframework.security.core.userdetails.UsernameNotFoundException;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;  

import com.shcal.model.Employee;
  
@Service  
@Transactional(readOnly=true)  
public class CustomUserDetailsService implements UserDetailsService {  
      
    @Autowired  
    private EmployeeService employeeService;      
  
    public UserDetails loadUserByUsername(String login)  
            throws UsernameNotFoundException {  
          
    	Employee domainUser = employeeService.findByemail(login);
          
        boolean enabled = domainUser.getActive();  
        boolean accountNonExpired = true;  
        boolean credentialsNonExpired = true;  
        boolean accountNonLocked = true;  
  
        return new User(  
                domainUser.getEmail(),   
                domainUser.getPwHash(),   
                enabled,   
                accountNonExpired,   
                credentialsNonExpired,   
                accountNonLocked,  
                getAuthorities(domainUser.getRole())  
        );  
    }        
    
    public Collection<? extends GrantedAuthority> getAuthorities(String permission) {   	  	
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(permission));  
        return authList;  
    }  
      
    
    
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {  
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();           
        for (String role : roles) {  
            authorities.add(new SimpleGrantedAuthority(role));  
        }  
        return authorities;  
    }    
    
    public List<String> getRoles(String role) {  
  
        List<String> roles = new ArrayList<String>();       
    	switch (role) {
		case "ADMIN":
			roles.add("ROLE_ADMIN");
			roles.add("ROLE_USER");
			break;
		case "USER":
			roles.add("ROLE_USER");
			break;	
		}        	      
        return roles;  
    }  
      
}  