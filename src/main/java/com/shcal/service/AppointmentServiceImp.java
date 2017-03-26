package com.shcal.service;

import java.util.Date;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shcal.exception.AppointmentNotFound;
import com.shcal.model.Appointment;
import com.shcal.repository.AppointmentRepository;


@Service
public class AppointmentServiceImp implements AppointmentService {
	
	@Resource
	private AppointmentRepository appointmentRepository;

	@Transactional
	public Appointment create(Appointment appointment){
		Appointment createdAppointment = appointment;
		return appointmentRepository.save(createdAppointment);
	}
	
	@Transactional(rollbackFor=AppointmentNotFound.class)
	public Appointment delete(Integer idAppointment) throws AppointmentNotFound {
		Appointment deletedAppointment = appointmentRepository.findOne(idAppointment);
		
		if(deletedAppointment == null){
			throw new AppointmentNotFound();
		}
		
		appointmentRepository.delete(deletedAppointment);
		return deletedAppointment;
	}
	
	@Transactional
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}
	
	@Transactional(rollbackFor=AppointmentNotFound.class)
	public Appointment update(Appointment appointment) throws AppointmentNotFound {
		Appointment updatedAppointment = appointmentRepository.findOne(appointment.getIdAppointment());
		
		if(updatedAppointment == null){
			throw new AppointmentNotFound();
		}
		
		updatedAppointment.setName(appointment.getName());
		updatedAppointment.setCategory(appointment.getCategory());
		updatedAppointment.setDate(appointment.getDate());
		
		updatedAppointment.setEmployee(appointment.getEmployee());

		updatedAppointment.setProject(appointment.getProject());


		return updatedAppointment;
	}
	
	@Override
	public Appointment findById(Integer id){
		return appointmentRepository.findOne(id);
	}

	@Override
	public List<Appointment> findByIdAppointmentIn(Collection<Integer> idAppointment) {
		return appointmentRepository.findByIdAppointmentIn(idAppointment);
	}
	
	@Override
	public List<Appointment> findByName(String name){
		return appointmentRepository.findByName(name);
	}
	
	@Override
	public Appointment findByDate(Date date){
		return appointmentRepository.findByDate(date);
	}

	@Override
	public List<Appointment> findAppointmentByWeek(Date monday) {
		final long append = 6 * 24 * 3600 * 1000; 
		Date sunday = new Date(monday.getTime() + append);
		return appointmentRepository.findAppointmentByWeek(monday, sunday);	
	}	
}
