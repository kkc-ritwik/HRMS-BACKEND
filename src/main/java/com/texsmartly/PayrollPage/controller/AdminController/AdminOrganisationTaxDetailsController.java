package com.texsmartly.PayrollPage.controller.AdminController;
import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.OrganisationTaxDetailsDTO;
import com.texsmartly.PayrollPage.service.interf.OrganisationTaxDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION+"/admin/organisation-tax-details")
public class AdminOrganisationTaxDetailsController {
    @Autowired
    private OrganisationTaxDetailsService organisationTaxDetailsService;

    // POST method to create new organization tax details
    @PostMapping
    public ResponseEntity<OrganisationTaxDetailsDTO> saveOrganisationTaxDetails(
            @RequestBody OrganisationTaxDetailsDTO organisationTaxDetailsDTO,
            Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        organisationTaxDetailsDTO.setUserId(principal.getName());
        OrganisationTaxDetailsDTO response = organisationTaxDetailsService.saveOrganisationTaxDetails(organisationTaxDetailsDTO);
        return ResponseEntity.ok(response);
    }

    // GET method to retrieve all organization tax details (Admin only)
    @GetMapping
    public ResponseEntity<List<OrganisationTaxDetailsDTO>> getAllOrganisationTaxDetails() {
        List<OrganisationTaxDetailsDTO> details = organisationTaxDetailsService.getAllOrganisationTaxDetails();
        return ResponseEntity.ok(details);
    }

    // GET method to retrieve organization tax details by user ID
    @GetMapping("/user")
    public ResponseEntity<List<OrganisationTaxDetailsDTO>> getOrganisationTaxDetailsByUser(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        List<OrganisationTaxDetailsDTO> detailsDTOS = organisationTaxDetailsService.getOrganisationTaxDetailsByUser(principal.getName());
        return ResponseEntity.ok(detailsDTOS);
    }
}
