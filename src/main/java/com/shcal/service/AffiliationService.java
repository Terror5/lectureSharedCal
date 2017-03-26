package com.shcal.service;

import java.util.Date;
import java.util.List;

import com.shcal.exception.AffiliationNotFound;
import com.shcal.model.Affiliation;
import com.shcal.model.Appointment;
import com.shcal.model.Employee;

public interface AffiliationService 
{
	public Affiliation createAffiliation(Affiliation affiliation);
    public Affiliation delete(int id) throws AffiliationNotFound;
    public List<Affiliation> findAll();
    public Affiliation update(Affiliation affiliation) throws AffiliationNotFound;
    
    public List<Affiliation> findByCreator(String creator);
	public List<Affiliation> findByDate(Date date);
	public List<Affiliation> findByEmployee_idEmployee(Employee employeeId);
	public List<Affiliation> findByAppointment_idAppointment(Appointment appointmentId); 
}
