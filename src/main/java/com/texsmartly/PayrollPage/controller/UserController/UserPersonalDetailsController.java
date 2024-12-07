package com.texsmartly.PayrollPage.controller.UserController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.PersonalDetailsDTO;
import com.texsmartly.PayrollPage.service.interf.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/user/personal-details")
public class UserPersonalDetailsController {

    @Autowired
    private PersonalDetailsService personalDetailsService;


    @GetMapping("/user")
    public ResponseEntity<List<PersonalDetailsDTO>> getPersonalDetailsByUser(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        List<PersonalDetailsDTO> personalDetailsDTOS = personalDetailsService.getPersonalDetailsByUser(principal.getName());
        return ResponseEntity.ok(personalDetailsDTOS);
    }

}
