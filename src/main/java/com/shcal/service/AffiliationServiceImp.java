package com.shcal.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shcal.exception.AffiliationNotFound;
import com.shcal.model.Affiliation;
import com.shcal.model.Appointment;
import com.shcal.model.Employee;
import com.shcal.repository.AffiliationRepository;

@Service
public class AffiliationServiceImp implements AffiliationService
{
	@Resource
	private AffiliationRepository affiliationRepository; 
	
	@Transactional
	public Affiliation createAffiliation(Affiliation affiliation) 
	{
		Affiliation createAffiliation = affiliation;
		return affiliationRepository.save(createAffiliation); 
	}

	@Transactional(rollbackFor = AffiliationNotFound.class)
	public Affiliation delete(int id) throws AffiliationNotFound 
	{
		Affiliation deleteAffiliation = affiliationRepository.findOne(id);
		
		if(deleteAffiliation == null)
		{
			throw new AffiliationNotFound();
		}
		
		affiliationRepository.delete(deleteAffiliation);
		return deleteAffiliation; 
	}

	@Transactional
	public List<Affiliation> findAll() 
	{
		return affiliationRepository.findAll();
	}

	@Transactional(rollbackFor = AffiliationNotFound.class)
	public Affiliation update(Affiliation affiliation) throws AffiliationNotFound 
	{
		Affiliation updateAffiliation = affiliationRepository.findOne(affiliation.getId().getEmployee_idEmployee());
		
		if(updateAffiliation == null)
		{
			throw new AffiliationNotFound(); 
		}
		
		updateAffiliation.setCreator(affiliation.getCreator());
		updateAffiliation.setDate(affiliation.getDate()); 
		updateAffiliation.setAppointment(affiliation.getAppointment());
		updateAffiliation.setEmployee(affiliation.getEmployee());
		
		return updateAffiliation; 
	}

	@Override
	public List<Affiliation> findByCreator(String creator) 
	{
		return affiliationRepository.findByCreator(creator);
	}

	@Override
	public List<Affiliation> findByDate(Date date) 
	{
		return affiliationRepository.findByDate(date);
	}

	@Override
	public List<Affiliation> findByEmployee_idEmployee(Employee employeeId) 
	{
		return affiliationRepository.findByEmployee_idEmployee(employeeId);
	}

	@Override
	public List<Affiliation> findByAppointment_idAppointment(Appointment appointmentId) 
	{
		return affiliationRepository.findByAppointment_idAppointment(appointmentId);
	}

}
