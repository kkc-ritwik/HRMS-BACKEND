package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.ITDeclarationsDTO;

import java.util.List;

public interface ITDeclarationService {
    // Method to save IT declarations
    ITDeclarationsDTO saveITDeclaration(ITDeclarationsDTO itDeclarationsDTO);

    // Method to retrieve all IT declarations
    List<ITDeclarationsDTO> getAllITDeclarations();

    // Method to retrieve IT declarations by user ID
    List<ITDeclarationsDTO> getITDeclarationsByUser(String userId);

    List<ITDeclarationsDTO> getITDeclarationsByUserId(String userId);

    ITDeclarationsDTO updateITDeclarationsByUserId(String userId, ITDeclarationsDTO updatedITDeclarationsDTO);

    // Method to delete IT declarations by user ID
    void deleteITDeclarationsByUserId(String userId);
}
