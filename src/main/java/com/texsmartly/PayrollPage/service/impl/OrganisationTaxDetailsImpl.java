package com.texsmartly.PayrollPage.service.impl;

import com.texsmartly.PayrollPage.dto.OrganisationTaxDetailsDTO;
import com.texsmartly.PayrollPage.model.settings.OrganisationTaxDetails;
import com.texsmartly.PayrollPage.repository.settings.OrganisationTaxDetailsRepository;
import com.texsmartly.PayrollPage.service.interf.OrganisationTaxDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class OrganisationTaxDetailsImpl implements OrganisationTaxDetailsService {
    @Autowired
    private OrganisationTaxDetailsRepository organisationTaxDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;
@Override
    // Save a new organization tax detail
    public OrganisationTaxDetailsDTO saveOrganisationTaxDetails(OrganisationTaxDetailsDTO organisationTaxDetailsDTO) {
        OrganisationTaxDetails organisationTaxDetails = modelMapper.map(organisationTaxDetailsDTO, OrganisationTaxDetails.class);
        organisationTaxDetails.setId(UUID.randomUUID().toString()); // Ensure a unique ID is set
        OrganisationTaxDetails savedOrganisationTaxDetails = organisationTaxDetailsRepository.save(organisationTaxDetails);
        return modelMapper.map(savedOrganisationTaxDetails, OrganisationTaxDetailsDTO.class);
    }
@Override
    // Get all organization tax details
    public List<OrganisationTaxDetailsDTO> getAllOrganisationTaxDetails() {
        List<OrganisationTaxDetails> detailsList = organisationTaxDetailsRepository.findAll();
        return detailsList.stream()
                .map(detail -> modelMapper.map(detail, OrganisationTaxDetailsDTO.class))
                .collect(Collectors.toList());
    }
@Override
    // Get organization tax details by user ID
    public List<OrganisationTaxDetailsDTO> getOrganisationTaxDetailsByUser(String userId) {
        List<OrganisationTaxDetails> detailsList = organisationTaxDetailsRepository.findByUserId(userId);
        return detailsList.stream()
                .map(record -> modelMapper.map(record, OrganisationTaxDetailsDTO.class))
                .collect(Collectors.toList());
    }
}
