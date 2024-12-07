package com.example.Recruitment.repository.form;

import com.example.Recruitment.model.form.JobRequisitionForm;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JobRequisitionFormRepository extends MongoRepository<JobRequisitionForm, String> {
    Optional<JobRequisitionForm> findByJobRequisitionId(String jobRequisitionId);
}