package com.texsmartly.PayrollPage.service.impl;

import com.texsmartly.PayrollPage.dto.TaxDeductorDetailsDTO;
import com.texsmartly.PayrollPage.model.settings.TaxDeductorDetails;
import com.texsmartly.PayrollPage.repository.settings.TaxDeductorDetailsRepository;
import com.texsmartly.PayrollPage.service.interf.TaxDeductorDetaisService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaxDeductorServiceImpl implements TaxDeductorDetaisService {
    @Autowired
    private TaxDeductorDetailsRepository taxDeductorDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override// Save a new tax deductor detail
    public TaxDeductorDetailsDTO saveTaxDeductorDetails(TaxDeductorDetailsDTO taxDeductorDetailsDTO) {
        TaxDeductorDetails taxDeductorDetails = modelMapper.map(taxDeductorDetailsDTO, TaxDeductorDetails.class);
        taxDeductorDetails.setId(UUID.randomUUID().toString()); // Ensure a unique ID is set
        TaxDeductorDetails savedTaxDeductor = taxDeductorDetailsRepository.save(taxDeductorDetails);
        return modelMapper.map(savedTaxDeductor, TaxDeductorDetailsDTO.class);
    }

    @Override// Get all tax deductor details
    public List<TaxDeductorDetailsDTO> getAllTaxDeductorDetails() {
        List<TaxDeductorDetails> detailsList = taxDeductorDetailsRepository.findAll();
        return detailsList.stream()
                .map(detail -> modelMapper.map(detail, TaxDeductorDetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override// Get tax deductor details by user ID
    public List<TaxDeductorDetailsDTO> getTaxDeductorDetailsByUser(String userId) {
        List<TaxDeductorDetails> detailsList = taxDeductorDetailsRepository.findByUserId(userId);
        return detailsList.stream()
                .map(record -> modelMapper.map(record, TaxDeductorDetailsDTO.class))
                .collect(Collectors.toList());
    }

}
