package com.sapient.cqrs_event_sourcing.utils;



public class KafkaMessageTemplate {
	
	long employeeid;
	String date;
	String firstSignin;
	String LastSignin;
	
	
	
	public KafkaMessageTemplate() {
		super();
		
	}
	public long getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(long employeeid) {
		this.employeeid = employeeid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFirstSignin() {
		return firstSignin;
	}
	public void setFirstSignin(String firstSignin) {
		this.firstSignin = firstSignin;
	}
	public String getLastSignin() {
		return LastSignin;
	}
	public void setLastSignin(String lastSignin) {
		LastSignin = lastSignin;
	}
	
	

}
