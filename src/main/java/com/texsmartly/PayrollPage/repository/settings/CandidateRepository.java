package com.texsmartly.PayrollPage.repository.settings;

import com.texsmartly.PayrollPage.model.settings.Candidates;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends MongoRepository<Candidates, String> {

}
