package com.texsmartly.PayrollPage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationDataDTO {
    private String employeeId;
    private String emailId;
    private String mobileNumber;
    private String otp;
    private LocalDateTime expiryTime;
}