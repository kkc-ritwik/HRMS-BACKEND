package com.texsmartly.PayrollPage.service.impl;

import com.texsmartly.PayrollPage.dto.ITDeclarationsDTO;
import com.texsmartly.PayrollPage.model.payroll.ITDeclarations;
import com.texsmartly.PayrollPage.repository.payroll.ITDeclarationRepository;
import com.texsmartly.PayrollPage.service.interf.ITDeclarationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ITDeclarationsServiceImpl implements ITDeclarationService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ITDeclarationRepository itDeclarationsRepository;

   @Override // Method to save IT declarations
    public ITDeclarationsDTO saveITDeclaration(ITDeclarationsDTO itDeclarationsDTO) {
        // Set a new ID for the IT declaration
        itDeclarationsDTO.setId(UUID.randomUUID().toString());

        // Map DTO to entity
        ITDeclarations itDeclarations = modelMapper.map(itDeclarationsDTO, ITDeclarations.class);

        // Save entity to the repository
        ITDeclarations savedITDeclarations = itDeclarationsRepository.save(itDeclarations);

        // Return the saved entity as a DTO
        return modelMapper.map(savedITDeclarations, ITDeclarationsDTO.class);
    }
@Override
    // Method to retrieve all IT declarations
    public List<ITDeclarationsDTO> getAllITDeclarations() {
        List<ITDeclarations> itDeclarationsList = itDeclarationsRepository.findAll();
        return itDeclarationsList.stream()
                .map(itDeclaration -> modelMapper.map(itDeclaration, ITDeclarationsDTO.class))
                .collect(Collectors.toList());
    }
@Override
    // Method to retrieve IT declarations by user ID
    public List<ITDeclarationsDTO> getITDeclarationsByUser(String userId) {
        List<ITDeclarations> itDeclarationsList = itDeclarationsRepository.findByUserId(userId);
        return itDeclarationsList.stream()
                .map(record -> modelMapper.map(record, ITDeclarationsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ITDeclarationsDTO> getITDeclarationsByUserId(String userId) {
        List<ITDeclarations> itDeclarationsList = itDeclarationsRepository.findByUserId(userId);
        return itDeclarationsList.stream()
                .map(record -> modelMapper.map(record, ITDeclarationsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ITDeclarationsDTO updateITDeclarationsByUserId(String userId, ITDeclarationsDTO updatedITDeclarationsDTO) {
        // Find the existing IT declaration record by user ID
        ITDeclarations existingRecord = itDeclarationsRepository.findByUserId(userId).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("IT Declaration not found for user ID: " + userId));

        // Map only non-null fields from DTO to existing entity using ModelMapper
        modelMapper.map(updatedITDeclarationsDTO, existingRecord);

        // Save the updated record back to the repository
        ITDeclarations updatedRecord = itDeclarationsRepository.save(existingRecord);

        // Return the updated record as a DTO
        return modelMapper.map(updatedRecord, ITDeclarationsDTO.class);
}


    // Method to delete IT declarations by user ID
    @Override
    public void deleteITDeclarationsByUserId(String userId) {
        List<ITDeclarations> itDeclarationsList = itDeclarationsRepository.findByUserId(userId);
        if (!itDeclarationsList.isEmpty()) {
            itDeclarationsRepository.deleteAll(itDeclarationsList);
        } else {
            throw new RuntimeException("No IT Declarations found for user ID: " + userId);
        }
    }
}
