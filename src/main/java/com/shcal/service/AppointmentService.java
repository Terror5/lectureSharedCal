// Felix Bossert, 29.5.2015
package com.shcal.service;

import java.util.Date;
import java.util.Collection;
import java.util.List;
import com.shcal.exception.AppointmentNotFound;
import com.shcal.model.Appointment;


public interface AppointmentService {
	
	public Appointment create(Appointment appointment);
    public Appointment delete(Integer id) throws AppointmentNotFound;
    public List<Appointment> findAll();
    public Appointment update(Appointment appointment) throws AppointmentNotFound;
    public Appointment findById(Integer id);
	public List<Appointment> findByName(String name);
	public List<Appointment> findByIdAppointmentIn(Collection<Integer> idAppointment);
	public Appointment findByDate(Date date);

	public List<Appointment> findAppointmentByWeek(Date monday);
}
