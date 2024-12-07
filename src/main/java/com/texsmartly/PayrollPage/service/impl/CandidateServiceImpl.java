package com.texsmartly.PayrollPage.service.impl;


import com.texsmartly.PayrollPage.dto.CandidatesDTO;
import com.texsmartly.PayrollPage.dto.SalaryStructureDTO;
import com.texsmartly.PayrollPage.model.settings.Candidates;
import com.texsmartly.PayrollPage.model.settings.SalaryStructure;
import com.texsmartly.PayrollPage.repository.settings.CandidateRepository;
import com.texsmartly.PayrollPage.service.interf.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidatesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CandidatesDTO> getAllCandidates() {
        return candidatesRepository.findAll().stream()
                .map(candidate -> modelMapper.map(candidate, CandidatesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CandidatesDTO getCandidateById(String id) {
        Candidates candidates = candidatesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
        return modelMapper.map(candidates, CandidatesDTO.class);
    }

    @Override
    public CandidatesDTO createCandidate(CandidatesDTO candidatesDTO) {
        Candidates candidates = modelMapper.map(candidatesDTO, Candidates.class);
        Candidates savedCandidates = candidatesRepository.save(candidates);
        return modelMapper.map(savedCandidates, CandidatesDTO.class);
    }

    @Override
    public CandidatesDTO updateCandidate(String id, CandidatesDTO candidatesDTO) {
        Candidates existingCandidates = candidatesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        modelMapper.map(candidatesDTO, existingCandidates);
        existingCandidates.setId(id); // Ensure ID is not overwritten

        Candidates updatedCandidates = candidatesRepository.save(existingCandidates);
        return modelMapper.map(updatedCandidates, CandidatesDTO.class);
    }

    @Override
    public void deleteCandidate(String id) {
        candidatesRepository.deleteById(id);
    }

    @Override
    public CandidatesDTO updateCandidateSalary(String id, List<SalaryStructureDTO> salaryStructureDTOs) {
        Candidates candidates = candidatesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        candidates.setSalaryStructures(salaryStructureDTOs.stream()
                .map(dto -> modelMapper.map(dto, SalaryStructure.class))
                .collect(Collectors.toList()));

        Candidates updatedCandidates = candidatesRepository.save(candidates);
        return modelMapper.map(updatedCandidates, CandidatesDTO.class);
    }
}

