package com.shcal.service;

import java.util.Collection;
import java.util.List;

import com.shcal.exception.EnterpriseNotFound;
import com.shcal.model.Enterprise;


public interface EnterpriseService {
        
        public Enterprise create(Enterprise enterprise);
        public Enterprise delete(Integer id) throws EnterpriseNotFound;
        public List<Enterprise> findAll();
        public Enterprise update(Enterprise enterprise) throws EnterpriseNotFound;
        public Enterprise findById(Integer id);
        
    	public List<Enterprise> findByName(String name);
    	public List<Enterprise> findByIdEnterpriseIn(Collection<Integer> idEnterprises);
    	public Enterprise findByContact(String contact);
    	public Enterprise findByEmail(String email);
		

}
