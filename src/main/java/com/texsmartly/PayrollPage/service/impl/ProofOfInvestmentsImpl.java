package com.texsmartly.PayrollPage.service.impl;
import com.texsmartly.PayrollPage.dto.ProofOfInvestmentsDTO;
import com.texsmartly.PayrollPage.model.payroll.ProofOfInvestments;
import com.texsmartly.PayrollPage.repository.payroll.ProofOfInvestmentsRepository;
import com.texsmartly.PayrollPage.service.interf.ProofOfInvestmentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class ProofOfInvestmentsImpl implements ProofOfInvestmentsService {
    @Autowired
    private ProofOfInvestmentsRepository proofOfInvestmentsRepository;

    @Autowired
    private ModelMapper modelMapper;
@Override
    // Save a new proof of investments
    public ProofOfInvestmentsDTO saveProofOfInvestments(ProofOfInvestmentsDTO proofOfInvestmentsDTO) {
        ProofOfInvestments proofOfInvestments = modelMapper.map(proofOfInvestmentsDTO, ProofOfInvestments.class);
        proofOfInvestments.setId(UUID.randomUUID().toString()); // Ensure a unique ID is set
        ProofOfInvestments savedProof = proofOfInvestmentsRepository.save(proofOfInvestments);
        return modelMapper.map(savedProof, ProofOfInvestmentsDTO.class);
    }

    @Override// Get all proof of investments
    public List<ProofOfInvestmentsDTO> getAllProofOfInvestments() {
        List<ProofOfInvestments> investmentsList = proofOfInvestmentsRepository.findAll();
        return investmentsList.stream()
                .map(investment -> modelMapper.map(investment, ProofOfInvestmentsDTO.class))
                .collect(Collectors.toList());
    }

    @Override// Get proof of investments by user ID
    public List<ProofOfInvestmentsDTO> getProofOfInvestmentsByUser(String userId) {
        List<ProofOfInvestments> investmentsList = proofOfInvestmentsRepository.findByUserId(userId);
        return investmentsList.stream()
                .map(record -> modelMapper.map(record, ProofOfInvestmentsDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public ProofOfInvestmentsDTO getProofOfInvestmentsByUserId(String userId) {
        return proofOfInvestmentsRepository.findByUserId(userId).stream()
                .findFirst()
                .map(proofOfInvestments -> modelMapper.map(proofOfInvestments, ProofOfInvestmentsDTO.class))
                .orElse(null);
    }

}
