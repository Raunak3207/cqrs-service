package com.sapient.cqrs_event_sourcing.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.cqrs_event_sourcing.controllers.dto.AttendenceEvent;
import com.sapient.cqrs_event_sourcing.dao.EventStore;
import com.sapient.cqrs_event_sourcing.utils.EventType;
import com.sapient.cqrs_event_sourcing.utils.KafkaMessageTemplate;

@Service
public class AttendenceService {
	
	@Autowired
	EventStore eventStore;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${kafkatopic}")
	String topicname;

	public boolean fisrtIn(long employeeid, EventType firstInEvent) {
		AttendenceEvent event = new AttendenceEvent();
		event.setEmployeeID(employeeid);
		event.setEvent(firstInEvent);
		try {
		eventStore.save(event);
		}catch(Exception ex) {
			return false;
		}
		return true;
		
	}

	public boolean in(long employeeid, EventType inEvent) {
		AttendenceEvent event = new AttendenceEvent();
		event.setEmployeeID(employeeid);
		event.setEvent(inEvent);
		try {
			eventStore.save(event);
			}catch(Exception ex) {
				return false;
			}
			return true;
	}

	public boolean out(long employeeid, EventType outEvent) {
		AttendenceEvent event = new AttendenceEvent();
		event.setEmployeeID(employeeid);
		event.setEvent(outEvent);
		try {
			eventStore.save(event);
			}catch(Exception ex) {
				return false;
			}
			return true;
	}

	public boolean lastout(long employeeid, EventType lastOutEvent) {
		AttendenceEvent event = new AttendenceEvent();
		event.setEmployeeID(employeeid);
		event.setEvent(lastOutEvent);
		try {
			eventStore.save(event);
			KafkaMessageTemplate message  =new KafkaMessageTemplate();
			message.setEmployeeid(employeeid);
			message.setDate(LocalDate.now().toString());
			message.setFirstSignin(getFirst(employeeid).toString());
			message.setLastSignin(getLast(employeeid).toString());
			ObjectMapper objectMapper  =new ObjectMapper();
			String data  = objectMapper.writeValueAsString(message);
			kafkaTemplate.send(topicname,data);
			
			}catch(Exception ex) {
				return false;
			}
			return true;
	}

	public LocalTime getFirst(long employeeid) {
		AttendenceEvent event = new AttendenceEvent();
		event.setEmployeeID(employeeid);
		event.setEvent(EventType.FIRST_IN_EVENT);
		Query query = new Query();
		query.addCriteria(Criteria.where("date").is(LocalDate.now()));
		return mongoTemplate.find(query, AttendenceEvent.class).get(0).getTime();
	}
	
	public LocalTime getLast(long employeeid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("date").is(LocalDate.now()));
		List<AttendenceEvent> lst  =mongoTemplate.find(query, AttendenceEvent.class);
		return lst.get(lst.size()-1).getTime();
	}

}
