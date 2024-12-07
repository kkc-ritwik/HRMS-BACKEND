package com.texsmartly.PayrollPage.repository.settings;

import com.texsmartly.PayrollPage.model.verification.VerificationData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VerificationRepository extends MongoRepository<VerificationData,String> {
    Optional<VerificationData> findFirstBy();

}
