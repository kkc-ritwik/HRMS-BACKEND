package com.texsmartly.PayrollPage.controller.CommonController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.PersonalDetailsDTO;

import com.texsmartly.PayrollPage.service.interf.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@CrossOrigin("*")
@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/common/personal-details")
public class CommonPersonalDetailsController {
    @Autowired
    private PersonalDetailsService personalDetailsService;
    @PostMapping
    public ResponseEntity<PersonalDetailsDTO> savePersonalDetails(@RequestBody PersonalDetailsDTO personalDetailsDTO, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        personalDetailsDTO.setUserId(principal.getName());
        PersonalDetailsDTO response = personalDetailsService.savePersonalDetails(personalDetailsDTO);
        return ResponseEntity.ok(response);
    }

    
}
