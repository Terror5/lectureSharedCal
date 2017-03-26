package com.shcal.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shcal.exception.EnterpriseNotFound;
import com.shcal.model.Enterprise;
import com.shcal.repository.EnterpriseRepository;


@Service
public class EnterpriseServiceImp implements EnterpriseService {

	@Resource
	private EnterpriseRepository enterpriseRepository;
	

	@Transactional
	public Enterprise create(Enterprise enterprise) {
		Enterprise createdEnterprise = enterprise;
		return enterpriseRepository.save(createdEnterprise);
	}


	@Transactional(rollbackFor=EnterpriseNotFound.class)
	public Enterprise delete(Integer id) throws EnterpriseNotFound {
		Enterprise deletedEnterprise = enterpriseRepository.findOne(id);
		
		if(deletedEnterprise == null){
			throw new EnterpriseNotFound();
		}
		
		enterpriseRepository.delete(deletedEnterprise);
		return deletedEnterprise;
	}


	@Transactional
	public List<Enterprise> findAll() {
		return enterpriseRepository.findAll();
	}


	@Transactional(rollbackFor=EnterpriseNotFound.class)
	public Enterprise update(Enterprise enterprise) throws EnterpriseNotFound {
		Enterprise updatedEnterprise = enterpriseRepository.findOne(enterprise.getIdEnterprise());
		
		if(updatedEnterprise == null){
			throw new EnterpriseNotFound();
		}
		
		updatedEnterprise.setName(enterprise.getName());
		updatedEnterprise.setAddress(enterprise.getAddress());
		updatedEnterprise.setEmail(enterprise.getEmail());
		updatedEnterprise.setContactPerson(enterprise.getContactPerson());
		return updatedEnterprise;
	}


	@Override
	public List<Enterprise> findByIdEnterpriseIn(Collection<Integer> idEnterprise) {
		return enterpriseRepository.findByIdEnterpriseIn(idEnterprise);
	}


	@Override
	public Enterprise findByContact(String contact) {
		return enterpriseRepository.findByContactPerson(contact);
	}


	@Override
	public List<Enterprise> findByName(String Name) {
		return enterpriseRepository.findByName(Name);
	}


	@Override
	public Enterprise findById(Integer id) {
		return enterpriseRepository.findOne(id);
	}


	@Override
	public Enterprise findByEmail(String emain) {
		return enterpriseRepository.findByEmail(emain);
	}


	



}
