package com.example.Recruitment.service.form;

import com.example.Recruitment.dto.form.JobRequisitionFormDTO;
import com.example.Recruitment.model.form.CandidateApplicationForm;
import com.example.Recruitment.model.form.JobRequisitionForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobRequisitionFormService {
    //    JobRequisitionFormDTO createJobRequisitionForm(JobRequisitionFormDTO formDTO, MultipartFile jobDescription);
    JobRequisitionFormDTO updateJobRequisitionForm(String id, JobRequisitionFormDTO formDTO, MultipartFile jobDescription);
    JobRequisitionFormDTO getJobRequisitionFormById(String id);
    List<JobRequisitionFormDTO> getAllJobRequisitionForms();
    void deleteJobRequisitionForm(String id);
    JobRequisitionFormDTO createJobRequisition(JobRequisitionFormDTO jobRequisitionDTO);
    JobRequisitionForm getJobRequisitionById(String jobRequisitionId);
    List<CandidateApplicationForm> getCandidatesByJobRequisitionId(String jobRequisitionId);
}