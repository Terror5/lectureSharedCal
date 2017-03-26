package com.shcal.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.shcal.model.Enterprise;

@Component
public class EnterpriseFormatter implements Formatter<Enterprise> {
	
	@Override
	public String print(Enterprise enterprise, Locale locale) {
		return String.valueOf(enterprise.getIdEnterprise());
	}

	@Override
	public Enterprise parse(String formatted, Locale locale) throws ParseException {
		
		Enterprise enterprise = new Enterprise();
		int id  = -1;
		if(!formatted.contains("-")){
			id = Integer.parseInt(formatted);	
		}
		enterprise.setIdEnterprise(id);
		return enterprise;
	}
}
