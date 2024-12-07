package com.texsmartly.PayrollPage.repository;

import com.texsmartly.PayrollPage.model.payroll.ClaimNumberCounter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClaimCounterRepository extends MongoRepository<ClaimNumberCounter, String> {
}
