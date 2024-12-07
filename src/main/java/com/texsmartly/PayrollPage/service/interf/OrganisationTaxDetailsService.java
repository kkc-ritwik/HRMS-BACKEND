package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.OrganisationTaxDetailsDTO;

import java.util.List;

public interface OrganisationTaxDetailsService {


    // Save a new organization tax detail
    OrganisationTaxDetailsDTO saveOrganisationTaxDetails(OrganisationTaxDetailsDTO organisationTaxDetailsDTO);

    // Get all organization tax details
    List<OrganisationTaxDetailsDTO> getAllOrganisationTaxDetails();

    // Get organization tax details by user ID
    List<OrganisationTaxDetailsDTO> getOrganisationTaxDetailsByUser(String userId);
}
