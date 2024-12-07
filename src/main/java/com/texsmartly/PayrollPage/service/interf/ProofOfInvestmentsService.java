package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.ProofOfInvestmentsDTO;

import java.util.List;

public interface ProofOfInvestmentsService {
    // Save a new proof of investments
    ProofOfInvestmentsDTO saveProofOfInvestments(ProofOfInvestmentsDTO proofOfInvestmentsDTO);

    // Get all proof of investments
    List<ProofOfInvestmentsDTO> getAllProofOfInvestments();

    // Get proof of investments by user ID
    List<ProofOfInvestmentsDTO> getProofOfInvestmentsByUser(String userId);

    ProofOfInvestmentsDTO getProofOfInvestmentsByUserId(String userId);
}
