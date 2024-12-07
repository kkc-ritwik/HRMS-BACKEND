package com.texsmartly.PayrollPage.repository.settings;

import com.texsmartly.PayrollPage.model.settings.TaxDeductorDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaxDeductorDetailsRepository extends MongoRepository<TaxDeductorDetails, String> {
    List<TaxDeductorDetails> findByUserId(String userId);
}
