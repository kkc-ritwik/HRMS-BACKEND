package com.texsmartly.PayrollPage.repository.payroll;

import com.texsmartly.PayrollPage.model.payroll.EmployeeSalaryDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeSalaryDetailsRepository extends MongoRepository<EmployeeSalaryDetails, String> {
    List<EmployeeSalaryDetails> findByUserId(String userId);

}
