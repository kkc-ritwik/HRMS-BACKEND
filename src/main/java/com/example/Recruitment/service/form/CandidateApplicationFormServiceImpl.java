
package com.example.Recruitment.service.form;

import com.example.Recruitment.dto.form.CandidateApplicationFormDTO;
import com.example.Recruitment.model.form.CandidateApplicationForm;
import com.example.Recruitment.model.form.Round;
import com.example.Recruitment.repository.form.CandidateApplicationFormRepository;
import com.example.Recruitment.repository.form.JobRequisitionFormRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateApplicationFormServiceImpl implements CandidateApplicationFormService {
    @Autowired
    private CandidateApplicationFormRepository candidateApplicationFormRepository;
    @Autowired
    private JobRequisitionFormRepository jobRequisitionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CandidateApplicationFormDTO> getAllCandidateForms() {
        return candidateApplicationFormRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateApplicationFormDTO getCandidateFormById(String id) {
        CandidateApplicationForm form = candidateApplicationFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate form not found"));
        return convertToDto(form);
    }

    @Override
    public CandidateApplicationFormDTO createCandidateForm(CandidateApplicationFormDTO candidateApplicationFormDTO, MultipartFile resume) {
        CandidateApplicationForm form = convertToEntity(candidateApplicationFormDTO);
        // The candidateId will be automatically generated in the constructor
        if (resume != null && !resume.isEmpty()) {
            try {
                form.setResume(resume.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error saving resume", e);
            }
        }
        form = candidateApplicationFormRepository.save(form);
        return convertToDto(form);
    }

    @Override
    public CandidateApplicationFormDTO updateCandidateForm(String id, CandidateApplicationFormDTO candidateApplicationFormDTO, MultipartFile resume) {
        CandidateApplicationForm form = candidateApplicationFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate form not found"));
        modelMapper.map(candidateApplicationFormDTO, form);
        if (resume != null && !resume.isEmpty()) {
            try {
                form.setResume(resume.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error saving resume", e);
            }
        }
        form = candidateApplicationFormRepository.save(form);
        return convertToDto(form);
    }

    @Override
    public void deleteCandidateForm(String id) {
        candidateApplicationFormRepository.deleteById(id);
    }

    private CandidateApplicationFormDTO convertToDto(CandidateApplicationForm form) {
        return modelMapper.map(form, CandidateApplicationFormDTO.class);
    }


    private CandidateApplicationForm convertToEntity(CandidateApplicationFormDTO formDTO) {
        return modelMapper.map(formDTO, CandidateApplicationForm.class);

    }

    @Override
    public CandidateApplicationFormDTO applyForJob(CandidateApplicationFormDTO candidateDTO) {
        try {
            // Validate job requisition ID
            String jobReqId = candidateDTO.getJobRequisitionId();
            if (jobReqId == null || jobReqId.trim().isEmpty()) {
                throw new IllegalArgumentException("Job Requisition ID is required");
            }

            // Check if job requisition exists
            if (!jobRequisitionRepository.findByJobRequisitionId(jobReqId).isPresent()) {
                throw new IllegalArgumentException("Job Requisition ID " + jobReqId + " not found");
            }

            // Map DTO to entity
            CandidateApplicationForm candidateApplicationForm = modelMapper.map(candidateDTO, CandidateApplicationForm.class);

            // Save the entity
            candidateApplicationForm = candidateApplicationFormRepository.save(candidateApplicationForm);

            // Map back to DTO and return
            return modelMapper.map(candidateApplicationForm, CandidateApplicationFormDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process application: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CandidateApplicationForm> getCandidatesByJobRequisitionId(String jobRequisitionId) {
        return candidateApplicationFormRepository.findByJobRequisitionId(jobRequisitionId);
    }

    @Override
    public CandidateApplicationFormDTO updateCandidateRounds(String id, Round round1, Round round2, Round round3) {
        CandidateApplicationForm form = candidateApplicationFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate form not found"));

        form.setRound1(round1);
        form.setRound2(round2);
        form.setRound3(round3);

        form = candidateApplicationFormRepository.save(form);
        return convertToDto(form);
    }
}