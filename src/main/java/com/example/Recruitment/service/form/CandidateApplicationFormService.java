package com.example.Recruitment.service.form;

import com.example.Recruitment.dto.form.CandidateApplicationFormDTO;
import com.example.Recruitment.model.form.CandidateApplicationForm;
import com.example.Recruitment.model.form.Round;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CandidateApplicationFormService {
    List<CandidateApplicationFormDTO> getAllCandidateForms();

    CandidateApplicationFormDTO getCandidateFormById(String id);

    CandidateApplicationFormDTO createCandidateForm(CandidateApplicationFormDTO candidateApplicationFormDTO, MultipartFile resume);

    CandidateApplicationFormDTO updateCandidateForm(String id, CandidateApplicationFormDTO candidateApplicationFormDTO, MultipartFile resume);

    void deleteCandidateForm(String id);

    CandidateApplicationFormDTO applyForJob(CandidateApplicationFormDTO candidateDTO);
    List<CandidateApplicationForm> getCandidatesByJobRequisitionId(String jobRequisitionId);

    CandidateApplicationFormDTO updateCandidateRounds(String id, Round round1, Round round2, Round round3);

}