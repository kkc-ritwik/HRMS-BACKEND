package com.example.Recruitment.repository;

import com.example.Recruitment.model.InterviewScheduling;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterviewSchedulingRepository extends MongoRepository<InterviewScheduling, String> {
}