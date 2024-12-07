package com.example.Recruitment.repository;

import com.example.Recruitment.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    List<Feedback> findByCandidateIdOrderByCreatedAtDesc(String candidateId);
}