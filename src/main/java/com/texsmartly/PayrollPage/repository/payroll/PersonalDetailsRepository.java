package com.texsmartly.PayrollPage.repository.payroll;

import com.texsmartly.PayrollPage.model.payroll.PersonalDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonalDetailsRepository extends MongoRepository<PersonalDetails, String> {
    List<PersonalDetails> findByUserId(String userId);

}
