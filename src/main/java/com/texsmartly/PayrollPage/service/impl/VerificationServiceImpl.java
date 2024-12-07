package com.texsmartly.PayrollPage.service.impl;


import com.texsmartly.PayrollPage.dto.VerificationDataDTO;
import com.texsmartly.PayrollPage.model.verification.VerificationData;
import com.texsmartly.PayrollPage.repository.settings.VerificationRepository;
import com.texsmartly.PayrollPage.service.interf.VerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationServiceImpl implements VerificationService {
    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VerificationDataDTO saveVerificationData(VerificationData verificationData){
        // Save the verification data in the database
        verificationRepository.save(verificationData);

        return modelMapper.map(verificationData,VerificationDataDTO.class);
    }

    // New method to get a single verification data
    public VerificationDataDTO getVerificationData() {
        Optional<VerificationData> optionalVerificationData = verificationRepository.findFirstBy();
        if(optionalVerificationData.isPresent()){
            return modelMapper.map(optionalVerificationData.get(), VerificationDataDTO.class);
        } else {
            throw new RuntimeException("No verification data found");
        }
    }

}
