package com.shcal.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shcal.model.Affiliation;
import com.shcal.model.Appointment;
import com.shcal.model.Employee;

public interface AffiliationRepository extends JpaRepository<Affiliation, Integer>
{
	public List<Affiliation> findByCreator(String creator);
	public List<Affiliation> findByDate(Date date);
	public List<Affiliation> findByEmployee_idEmployee(Employee employeeId);
	public List<Affiliation> findByAppointment_idAppointment(Appointment appointmentId); 
}
