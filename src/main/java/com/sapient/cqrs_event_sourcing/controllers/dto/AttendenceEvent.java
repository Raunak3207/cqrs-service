package com.sapient.cqrs_event_sourcing.controllers.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.sapient.cqrs_event_sourcing.utils.EventType;

@Document
public class AttendenceEvent {
	
	EventType event;
	Long employeeID;
	LocalDate date;
	LocalTime time;
	
	public AttendenceEvent() {
		this.date = LocalDate.now();
		this.time = LocalTime.now();
	}

	public EventType getEvent() {
		return event;
	}

	public void setEvent(EventType event) {
		this.event = event;
	}

	public Long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Long employeeID) {
		this.employeeID = employeeID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "AttendenceEvent [event=" + event + ", employeeID=" + employeeID + ", date=" + date + ", time=" + time
				+ "]";
	}

	
	
	
	

}
