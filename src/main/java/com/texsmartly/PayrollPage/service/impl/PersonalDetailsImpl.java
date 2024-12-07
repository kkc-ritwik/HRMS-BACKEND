package com.texsmartly.PayrollPage.service.impl;

import com.texsmartly.PayrollPage.dto.PersonalDetailsDTO;
import com.texsmartly.PayrollPage.model.payroll.PersonalDetails;
import com.texsmartly.PayrollPage.repository.payroll.PersonalDetailsRepository;
import com.texsmartly.PayrollPage.service.interf.PersonalDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonalDetailsImpl implements PersonalDetailsService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;


    @Override
    public PersonalDetailsDTO savePersonalDetails(PersonalDetailsDTO personalDetailsDTO) {
        PersonalDetails personalDetails = modelMapper.map(personalDetailsDTO, PersonalDetails.class);
        personalDetailsDTO.setId(UUID.randomUUID().toString());
        PersonalDetails savedPersonalDetails = personalDetailsRepository.save(personalDetails);
        return modelMapper.map(savedPersonalDetails, PersonalDetailsDTO.class);
    }


    @Override
    public List<PersonalDetailsDTO> getAllPersonalDetails() {
        List<PersonalDetails> personalDetailsList = personalDetailsRepository.findAll();
        return personalDetailsList.stream()
                .map(personalDetails -> modelMapper.map(personalDetails, PersonalDetailsDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<PersonalDetailsDTO> getPersonalDetailsByUser(String userId) {
        List<PersonalDetails> personalDetailsList = personalDetailsRepository.findByUserId(userId);
        return personalDetailsList.stream()
                .map(record -> modelMapper.map(record, PersonalDetailsDTO.class))
                .collect(Collectors.toList());
    }


}
