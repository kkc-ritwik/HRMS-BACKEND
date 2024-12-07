package com.texsmartly.PayrollPage.controller.AdminController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.TaxDeductorDetailsDTO;
import com.texsmartly.PayrollPage.service.interf.TaxDeductorDetaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping(ApiVersionConfig.API_VERSION+"/admin/tax-deductors")
public class AdminTaxDeductorDetailsController {
    // POST method to create new tax deductor details
    @Autowired
    private TaxDeductorDetaisService taxDeductorDetailsService;

    @PostMapping
    public ResponseEntity<TaxDeductorDetailsDTO> saveTaxDeductorDetails(
            @RequestBody TaxDeductorDetailsDTO taxDeductorDetailsDTO,
            Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        taxDeductorDetailsDTO.setUserId(principal.getName());
        TaxDeductorDetailsDTO response = taxDeductorDetailsService.saveTaxDeductorDetails(taxDeductorDetailsDTO);
        return ResponseEntity.ok(response);
    }

    // GET method to retrieve all tax deductor details (Admin only)
    @GetMapping
    public ResponseEntity<List<TaxDeductorDetailsDTO>> getAllTaxDeductorDetails() {
        List<TaxDeductorDetailsDTO> details = taxDeductorDetailsService.getAllTaxDeductorDetails();
        return ResponseEntity.ok(details);
    }

    // GET method to retrieve tax deductor details by user ID
    @GetMapping("/user")
    public ResponseEntity<List<TaxDeductorDetailsDTO>> getTaxDeductorDetailsByUser(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        List<TaxDeductorDetailsDTO> detailsDTOS = taxDeductorDetailsService.getTaxDeductorDetailsByUser(principal.getName());
        return ResponseEntity.ok(detailsDTOS);
    }
}
