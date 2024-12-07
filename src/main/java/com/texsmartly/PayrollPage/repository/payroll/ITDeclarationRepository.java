package com.texsmartly.PayrollPage.repository.payroll;

import com.texsmartly.PayrollPage.model.payroll.ITDeclarations;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITDeclarationRepository extends MongoRepository<ITDeclarations, String> {
    List<ITDeclarations> findByUserId(String userId);
}
