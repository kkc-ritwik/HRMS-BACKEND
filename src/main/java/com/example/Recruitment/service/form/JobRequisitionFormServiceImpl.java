package com.example.Recruitment.service.form;

import com.example.Recruitment.dto.form.JobRequisitionFormDTO;
import com.example.Recruitment.model.form.CandidateApplicationForm;
import com.example.Recruitment.model.form.JobRequisitionForm;
import com.example.Recruitment.repository.form.CandidateApplicationFormRepository;
import com.example.Recruitment.repository.form.JobRequisitionFormRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobRequisitionFormServiceImpl implements JobRequisitionFormService{
    @Autowired
    private JobRequisitionFormRepository jobRequisitionFormRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired private CandidateApplicationFormRepository candidateRepository;


    @Override
    public JobRequisitionFormDTO createJobRequisition(JobRequisitionFormDTO jobRequisitionDTO) {

        JobRequisitionForm jobRequisitionForm = jobRequisitionFormRepository.save(modelMapper.map(jobRequisitionDTO,JobRequisitionForm.class));
        return modelMapper.map(jobRequisitionForm,JobRequisitionFormDTO.class);
    }


    @Override
    public JobRequisitionForm getJobRequisitionById(String jobRequisitionId) {
        Optional<JobRequisitionForm> jobRequisition = jobRequisitionFormRepository.findByJobRequisitionId(jobRequisitionId);
        return jobRequisition.orElse(null);
    }

    @Override
    public List<CandidateApplicationForm> getCandidatesByJobRequisitionId(String jobRequisitionId) {
        return candidateRepository.findByJobRequisitionId(jobRequisitionId);
    }

    @Override
    public JobRequisitionFormDTO updateJobRequisitionForm(String id, JobRequisitionFormDTO formDTO, MultipartFile jobDescription) {
        Optional<JobRequisitionForm> optionalJobRequisitionForm = jobRequisitionFormRepository.findById(id);

        if (!optionalJobRequisitionForm.isPresent()) {
            throw new RuntimeException("Job Requisition Form not found");
        }

        JobRequisitionForm jobRequisitionForm = optionalJobRequisitionForm.get();
        modelMapper.map(formDTO, jobRequisitionForm);

        if (jobDescription != null && !jobDescription.isEmpty()) {
            try {
                jobRequisitionForm.setJobDescription(jobDescription.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error processing the job description file");
            }
        }

        JobRequisitionForm updatedForm = jobRequisitionFormRepository.save(jobRequisitionForm);
        return modelMapper.map(updatedForm, JobRequisitionFormDTO.class);
    }

    @Override
    public JobRequisitionFormDTO getJobRequisitionFormById(String id) {
        JobRequisitionForm jobRequisitionForm = jobRequisitionFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Requisition Form not found"));
        return modelMapper.map(jobRequisitionForm, JobRequisitionFormDTO.class);
    }

    @Override
    public List<JobRequisitionFormDTO> getAllJobRequisitionForms() {
        List<JobRequisitionForm> forms = jobRequisitionFormRepository.findAll();
        return forms.stream()
                .map(form -> {
                    JobRequisitionFormDTO dto = modelMapper.map(form, JobRequisitionFormDTO.class);
                    if (form.getJobDescription() != null) {
                        dto.setJobDescription(Base64.getEncoder().encodeToString(form.getJobDescription()));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteJobRequisitionForm(String id) {
        jobRequisitionFormRepository.deleteById(id);
    }
}