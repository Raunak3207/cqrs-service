package com.sapient.cqrs_event_sourcing.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sapient.cqrs_event_sourcing.controllers.dto.AttendenceEvent;

@Repository
public interface EventStore extends MongoRepository<AttendenceEvent, Long>	 {

}
