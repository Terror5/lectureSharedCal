// Felix Bossert, 29.5.2015
package com.shcal.repository;

import java.util.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shcal.model.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	public List<Appointment> findByIdAppointmentIn(Collection<Integer> idAppointment);
	public List<Appointment> findByName(String name);
	public Appointment findByDate(Date date);
	
    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.affiliations WHERE a.date >= :monday AND a.date <= :sunday")
	public List<Appointment> findAppointmentByWeek(@Param("monday") Date monday, @Param("sunday")Date sunday);
}
