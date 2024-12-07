package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.CandidatesDTO;
import com.texsmartly.PayrollPage.dto.SalaryStructureDTO;

import java.util.List;

public interface CandidateService {
    List<CandidatesDTO> getAllCandidates();
    CandidatesDTO getCandidateById(String id);
    CandidatesDTO createCandidate(CandidatesDTO candidateDTO);
    CandidatesDTO updateCandidate(String id, CandidatesDTO candidateDTO);
    void deleteCandidate(String id);
    CandidatesDTO updateCandidateSalary(String id, List<SalaryStructureDTO>salaryStructures);
}
