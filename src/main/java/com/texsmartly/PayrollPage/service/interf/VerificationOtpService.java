package com.texsmartly.PayrollPage.service.interf;

public interface VerificationOtpService {
    public String sendOtp(String email);
    public boolean verifyOtp(String email,String otp);
}