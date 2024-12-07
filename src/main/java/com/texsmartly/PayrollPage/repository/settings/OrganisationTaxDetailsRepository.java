package com.texsmartly.PayrollPage.repository.settings;

import com.texsmartly.PayrollPage.model.settings.OrganisationTaxDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrganisationTaxDetailsRepository extends MongoRepository<OrganisationTaxDetails, String> {
    List<OrganisationTaxDetails> findByUserId(String userId);
}
