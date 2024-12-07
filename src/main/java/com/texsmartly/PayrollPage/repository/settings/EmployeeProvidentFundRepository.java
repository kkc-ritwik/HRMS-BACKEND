package com.texsmartly.PayrollPage.repository.settings;

import com.texsmartly.PayrollPage.model.settings.EmployeeProvidentFund;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeProvidentFundRepository extends MongoRepository<EmployeeProvidentFund, String> {
    List<EmployeeProvidentFund> findByUserId(String userId);
}
