package com.texsmartly.PayrollPage.repository.payroll;

import com.texsmartly.PayrollPage.model.payroll.ProofOfInvestments;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProofOfInvestmentsRepository extends MongoRepository<ProofOfInvestments, String> {
    List<ProofOfInvestments> findByUserId(String userId);
}
