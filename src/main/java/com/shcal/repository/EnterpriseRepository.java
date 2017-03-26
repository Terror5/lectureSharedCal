package com.shcal.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shcal.model.Enterprise;


public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {
	
	public List<Enterprise> findByName(String Name);
	public List<Enterprise> findByIdEnterpriseIn(Collection<Integer> idEnterprise);
	public Enterprise findByContactPerson(String contactPerson);
	public Enterprise findByEmail(String email);
	
}