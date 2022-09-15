package com.sapient.cqrs_event_sourcing.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.cqrs_event_sourcing.service.AttendenceService;
import com.sapient.cqrs_event_sourcing.utils.EventType;

@RestController
public class AttendenceController {
	
	@Autowired
	AttendenceService attendenceService;
	
	@PostMapping("/fistin/{employeeid}")
	public String FirstIn(@PathVariable long employeeid) {
		attendenceService.fisrtIn(employeeid,EventType.FIRST_IN_EVENT);
		return "Welcome On the new Day";
		
	}
	
	@PostMapping("/in/{employeeid}")
	public String in(@PathVariable long employeeid) {
		attendenceService.in(employeeid,EventType.IN_EVENT);
		return "welcome back";
		
	}
	
	@PostMapping("/out/{employeeid}")
	public String out(@PathVariable long employeeid) {
		attendenceService.out(employeeid,EventType.OUT_EVENT);
		return "out of the office ";
		
	}
	
	@PostMapping("/lastout/{employeeid}")
	public String lastout(@PathVariable long employeeid) {
		attendenceService.lastout(employeeid,EventType.LAST_OUT_EVENT);
		return "see you on the next day";
		
	}
	
	@GetMapping("/getFirst/{employeeid}")
	public String getFirst(@PathVariable  long employeeid) {
		  return attendenceService.getFirst(employeeid).toString();
	}

}
