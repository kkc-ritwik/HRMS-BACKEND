package com.texsmartly.PayrollPage.repository.payroll;

import com.texsmartly.PayrollPage.model.payroll.LeaveDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveRepository extends MongoRepository<LeaveDetails, String> {
    Optional<LeaveDetails> findByUserId(String userId);
}
