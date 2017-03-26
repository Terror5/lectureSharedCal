package com.shcal.model;

public class Password {

	private String pwString1;
	private String pwString2;
	private String oldPw;
	private Employee employee;
	
	public Password(){
		
	}
	
	public Password(Employee employee){
		this.employee = employee;
	}

	public String getPwString1(){
		return pwString1;
	}
	
	public void setPwString1(String pwString1){
		this.pwString1 = pwString1;
	}
	
	public String getPwString2(){
		return pwString2;
	}
	
	public void setPwString2(String pwString2){
		this.pwString2 = pwString2;
	}
	
	public Employee getEmployee(){
		return employee;
	}
	
	public void setEmployee(Employee employee){
		this.employee = employee;
	}
	
	public void setOldPw(String oldPw){
		this.oldPw = oldPw;
	}
	
	public String getOldPw(){
		return oldPw;
	}
	
}
