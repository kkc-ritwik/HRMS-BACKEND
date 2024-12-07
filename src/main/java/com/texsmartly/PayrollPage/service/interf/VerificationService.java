package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.VerificationDataDTO;
import com.texsmartly.PayrollPage.model.verification.VerificationData;

public interface VerificationService {

    VerificationDataDTO saveVerificationData(VerificationData verificationData);

    VerificationDataDTO getVerificationData();
}
