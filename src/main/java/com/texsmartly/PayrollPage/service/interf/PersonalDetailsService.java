package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.PersonalDetailsDTO;

import java.util.List;

public interface PersonalDetailsService {

    PersonalDetailsDTO savePersonalDetails(PersonalDetailsDTO personalDetailsDTO);

    List<PersonalDetailsDTO> getAllPersonalDetails();


    List<PersonalDetailsDTO> getPersonalDetailsByUser(String userId);
}
