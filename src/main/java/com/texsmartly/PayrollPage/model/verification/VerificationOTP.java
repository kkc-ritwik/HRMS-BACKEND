package com.texsmartly.PayrollPage.model.verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "verification_otp")
public class VerificationOTP {
    @Id
    private String emailId;
    private String otp;
    private LocalDateTime expiryTime;
}