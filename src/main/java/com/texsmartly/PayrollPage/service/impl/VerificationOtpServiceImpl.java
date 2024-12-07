package com.texsmartly.PayrollPage.service.impl;
import com.texsmartly.PayrollPage.dto.VerificationDataDTO;
import com.texsmartly.PayrollPage.model.verification.VerificationOTP;
import com.texsmartly.PayrollPage.repository.settings.VerificationOTPRepository;
import com.texsmartly.PayrollPage.service.interf.VerificationOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class VerificationOtpServiceImpl implements VerificationOtpService {
    public final VerificationOTPRepository verificationOTPRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public VerificationOtpServiceImpl(VerificationOTPRepository verificationOTPRepository,
                                      JavaMailSender mailSender){
        this.verificationOTPRepository = verificationOTPRepository;
        this.mailSender = mailSender;
    }
    private static final int  OTP_EXPIRY_MINUTES = 5; // OTP expiry time

    @Override
    public String sendOtp(String email){
        String otp = generateOtp();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);

        VerificationOTP verificationOTP = new VerificationOTP();
        verificationOTP.setEmailId(email);
        verificationOTP.setOtp(otp);
        verificationOTP.setExpiryTime(expiryTime);

        verificationOTPRepository.save(verificationOTP);// Save OTP entity to the database

        sendEmail(email, "Your OTP","Your OTP is: " +otp);
        return otp;
    }

//    @Override
//    public boolean verifyOtp(String emailId,String otp){
//        VerificationOTP verificationOTP = verificationOTPRepository.findByEmailId(emailId);
//        if (verificationOTP != null && verificationOTP.getOtp().equals(otp) &&
//             LocalDateTime.now().isBefore(verificationOTP.getExpiryTime())){
//            verificationOTPRepository.deleteById(emailId);// Optionally delete OTP after verification
//            return true;
//        }
//        return false;
//    }

    // Method to verify OTP
    public boolean verifyOtp(String emailId, String otp) {
        if (emailId == null || emailId.isEmpty()) {
            throw new IllegalArgumentException("Email ID cannot be null or empty");
        }
        if (otp == null || otp.isEmpty()) {
            throw new IllegalArgumentException("OTP cannot be null or empty");
        }

        Optional<VerificationDataDTO> optionalVerificationData = verificationOTPRepository.findByEmailId(emailId);
        if (optionalVerificationData.isPresent()) {
            VerificationDataDTO verificationData = optionalVerificationData.get();
            if (verificationData.getOtp() == null) {
                throw new RuntimeException("No OTP has been generated for this email");
            }
            if (verificationData.getExpiryTime().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("OTP has expired");
            }
            if (verificationData.getOtp().equals(otp)) {
                // Optionally, clear the OTP after successful verification
                verificationData.setOtp(null);
                verificationData.setExpiryTime(null);
                VerificationOTPRepository.save(verificationData);
                return true;
            } else {
                throw new RuntimeException("Invalid OTP");
            }
        } else {
            throw new RuntimeException("No verification data found");
        }
    }
    private final Random random = new Random(); // Reusable Random instance

    private String generateOtp() {
        return String.format("%06d", random.nextInt(1000000)); // Use 1000000 to ensure a 6-digit OTP
    }

    private void sendEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("onboarding.test43@outlook.com");
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}

