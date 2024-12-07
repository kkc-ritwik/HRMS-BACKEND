package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.TaxDeductorDetailsDTO;

import java.util.List;

public interface TaxDeductorDetaisService {
    // Save a new tax deductor detail
    TaxDeductorDetailsDTO saveTaxDeductorDetails(TaxDeductorDetailsDTO taxDeductorDetailsDTO);

    // Get all tax deductor details
    List<TaxDeductorDetailsDTO> getAllTaxDeductorDetails();

    // Get tax deductor details by user ID
    List<TaxDeductorDetailsDTO> getTaxDeductorDetailsByUser(String userId);
}
