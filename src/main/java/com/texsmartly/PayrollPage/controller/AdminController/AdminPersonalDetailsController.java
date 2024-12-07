package com.texsmartly.PayrollPage.controller.AdminController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.PersonalDetailsDTO;
import com.texsmartly.PayrollPage.service.interf.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/admin/personal-details")
public class AdminPersonalDetailsController {


    @Autowired
    private PersonalDetailsService personalDetailsService;

    // Get all personal details (Admin only)
    @GetMapping
    public ResponseEntity<List<PersonalDetailsDTO>> getAllPersonalDetails() {
        List<PersonalDetailsDTO> personalDetails = personalDetailsService.getAllPersonalDetails();
        return ResponseEntity.ok(personalDetails);
    }

}
