package com.example.Recruitment.repository.calendar;

import com.example.Recruitment.model.calendar.Availability;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvailabilityRepository extends MongoRepository<Availability, String> {
}
