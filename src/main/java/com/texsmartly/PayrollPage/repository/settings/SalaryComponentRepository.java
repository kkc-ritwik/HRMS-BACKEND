package com.texsmartly.PayrollPage.repository.settings;

import com.texsmartly.PayrollPage.model.settings.SalaryComponent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SalaryComponentRepository extends MongoRepository<SalaryComponent, String> {
    List<SalaryComponent> findByUserId(String userId);
    Optional<SalaryComponent> findFirstByOrderByCreatedDateDesc();
}
