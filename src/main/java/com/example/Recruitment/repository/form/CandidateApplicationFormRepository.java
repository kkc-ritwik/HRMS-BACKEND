package com.example.Recruitment.repository.form;

import com.example.Recruitment.model.form.CandidateApplicationForm;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CandidateApplicationFormRepository extends MongoRepository<CandidateApplicationForm, String> {
    List<CandidateApplicationForm> findByJobRequisitionId(String jobRequisitionId);
}