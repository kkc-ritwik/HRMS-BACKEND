package com.example.Recruitment.repository;

import com.example.Recruitment.model.OfferApproval;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OfferApprovalRepository extends MongoRepository<OfferApproval, String> {
    List<OfferApproval> findByCandidateId(String candidateId);
    Optional<OfferApproval> findTopByCandidateIdOrderByCreatedAtDesc(String candidateId);
    List<OfferApproval> findByStatus(String status);
}