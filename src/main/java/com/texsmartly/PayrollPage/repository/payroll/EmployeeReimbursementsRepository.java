package com.texsmartly.PayrollPage.repository.payroll;

import com.texsmartly.PayrollPage.model.payroll.EmployeeReimbursements;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeReimbursementsRepository extends MongoRepository<EmployeeReimbursements, String> {
    List<EmployeeReimbursements> findByUserId(String userId);
}
