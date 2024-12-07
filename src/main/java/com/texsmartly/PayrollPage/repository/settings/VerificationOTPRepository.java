package com.texsmartly.PayrollPage.repository.settings;


import com.texsmartly.PayrollPage.dto.VerificationDataDTO;
import com.texsmartly.PayrollPage.model.verification.VerificationOTP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VerificationOTPRepository extends MongoRepository<VerificationOTP,String> {
    static void save(VerificationDataDTO verificationData) {
    }

    Optional<VerificationDataDTO> findByEmailId(String emailId);
}
