package com.shcal.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.shcal.model.Employee;

@Component
public class EmployeeFormatter implements Formatter<Employee> {
	
	@Override
	public String print(Employee employee, Locale locale) {
		return String.valueOf(employee.getIdEmployee());
	}

	@Override
	public Employee parse(String formatted, Locale locale) throws ParseException {
		return new Employee(Integer.parseInt(formatted));
	}

}
