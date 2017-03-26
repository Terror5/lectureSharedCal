package com.shcal.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shcal.exception.AppointmentNotFound;
import com.shcal.model.Appointment;
import com.shcal.model.AppointmentDetails;
import com.shcal.model.Employee;
import com.shcal.service.AppointmentService;
import com.shcal.service.EmployeeService;

@RestController
@RequestMapping("/secured/appointment")
public class AppointmentController {
	
	@Autowired
	public AppointmentService appointmentService;
	
	@Autowired
	public EmployeeService employeeService;

    @RequestMapping("/list/{monday}")
    public List<AppointmentDetails> getAppointments(@PathVariable String monday) {
    	
    	createSomeAppointments();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			appointments = appointmentService.findAppointmentByWeek(sdf.parse(monday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<AppointmentDetails> appDetailsList = new ArrayList<AppointmentDetails>();
		for(Appointment app : appointments)
		{
			appDetailsList.add(new AppointmentDetails(app));
		}
        return appDetailsList;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json",   
    		consumes = "application/json")
    public AppointmentDetails createAppointment(@RequestBody AppointmentDetails appointment, Principal principal) {
    	
    	if(appointment != null)
    	{
	    	Appointment app = appointment.convertAppointment();	    	
			Employee emp = employeeService.findByemail(principal.getName());
			app.setEmployee(emp);
			app = appointmentService.create(app); 
    	
			return new AppointmentDetails(app);
    	}
		return new AppointmentDetails();
    }
    
    @RequestMapping(value="/update")
    public Appointment updateAppointment(@ModelAttribute("appointment") Appointment appointment) {
    	
    	try {
			appointment = appointmentService.update(appointment);
		} catch (AppointmentNotFound e) {
			e.printStackTrace();
		}
    	return appointment;
    }
    
    @RequestMapping("/delete/{id}")
    public Appointment updateAppointment(@PathVariable Integer id) {
    	
    	Appointment appointment = null;
    	try {
			appointment = appointmentService.delete(id);
		} catch (AppointmentNotFound e) {
			e.printStackTrace();
		}
    	return appointment;
    }
   
    
    public void createSomeAppointments()
    {
    	if(appointmentService.findAppointmentByWeek(new Date()).size() == 0)
    	{
			Employee emp = employeeService.findByemail("admin@admin.de");
			for(int i = 1; i <= 7; i++)
			{
				createAppointment(i,"aa",emp);
				createAppointment(i,"bb",emp);
				createAppointment(i,"cc",emp);
				createAppointment(i+7,"dd",emp);
			}
    	}
    }
    
    public void createAppointment(int i, String seed,Employee emp)
    {
		final long append = i * 24 * 3600 * 1000;
		Date d = new Date();
		d = new Date(d.getTime() + append);
		Appointment a = new Appointment();
		a.setCategory("cat"+seed+i);
		a.setDate(d);
		a.setName("name"+seed+i);
		a.setEmployee(emp);
		appointmentService.create(a);    	
    }
}