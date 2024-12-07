package com.texsmartly.PayrollPage.controller.AdminController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.VerificationDataDTO;
import com.texsmartly.PayrollPage.model.verification.VerificationData;
import com.texsmartly.PayrollPage.model.verification.VerificationOTP;
import com.texsmartly.PayrollPage.repository.settings.VerificationOTPRepository;
import com.texsmartly.PayrollPage.service.interf.VerificationOtpService;
import com.texsmartly.PayrollPage.service.interf.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/admin")
public class AdminVerificationController {
    @Autowired
    private VerificationOTPRepository verificationOTPRepository;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private VerificationOtpService verificationOtpService;

    @PostMapping("/verification")
    public VerificationDataDTO saveVerificationData(@RequestBody VerificationData verificationData) {
        //verificationOTPRepository.save(verificationData);
        return verificationService.saveVerificationData(verificationData);
    }
    // New GET mapping to retrieve all verification data

    @GetMapping("/verification")
    public ResponseEntity<VerificationDataDTO> getVerificationData() {
        VerificationDataDTO verificationData = verificationService.getVerificationData();
        return ResponseEntity.ok(verificationData);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendOTP(@RequestBody VerificationOTP verificationOTP){
        try {
            // Ensure emailId is passed in the request
            if (verificationOTP.getEmailId() == null || verificationOTP.getEmailId().isEmpty()) {
                return ResponseEntity.badRequest().body("Email ID is required to send OTP");
            }
            verificationOtpService.sendOtp(verificationOTP.getEmailId());
            return ResponseEntity.ok("OTP sent to email");
        }catch (Exception e){
            // Log the exception for debugging
            System.err.println("Error occurred while sending OTP: " + e.getMessage());
            return ResponseEntity.status(500).body("Failed to send OTP");
        }
    }
    // POST endpoint to resend OTP
    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOtp(@RequestBody Map<String, String> request) {
        String emailId = request.get("emailId");
        try {
            verificationOtpService.sendOtp(emailId);
            return ResponseEntity.ok("OTP resent to email");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to resend OTP: " + e.getMessage());
        }
    }

    // POST endpoint to verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> request) {
        String emailId = request.get("emailId");
        String otp = request.get("otp");
        try {
            boolean isVerified = verificationOtpService.verifyOtp(emailId, otp);
            if (isVerified) {
                return ResponseEntity.ok("OTP verified successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
