package com.example.Recruitment.controller.form;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.form.JobRequisitionFormDTO;
import com.example.Recruitment.model.form.CandidateApplicationForm;
import com.example.Recruitment.model.form.JobRequisitionForm;
import com.example.Recruitment.service.form.CandidateApplicationFormService;
import com.example.Recruitment.service.form.JobRequisitionFormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/job-Form")
@CrossOrigin("*")
public class JobRequisitionFormController {

    @Autowired
    private JobRequisitionFormService jobRequisitionFormService;
    @Autowired
    private CandidateApplicationFormService candidateApplicationFormService;
    @Autowired
    private ObjectMapper objectMapper;

    // Exception handler to provide detailed validation error responses
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    // Create a new job requisition
//    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> createJobRequisition(@RequestParam String jobRequisitionDTO,
//                                                  @RequestParam MultipartFile jobDescription) {
//        if (!isValidJson(jobRequisitionDTO)) {
//            return ResponseEntity.badRequest().body("Invalid JSON Data");
//        }
//        try {
//            JobRequisitionFormDTO jobRequisitionFormDTO = objectMapper.readValue(jobRequisitionDTO, JobRequisitionFormDTO.class);
//
//            // Validate if job description file is empty
//            if (jobDescription.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Job description file is required");
//            }
//
//            jobRequisitionFormDTO.setJobDescription(jobDescription.getBytes());
//            JobRequisitionFormDTO savedJobRequisition = jobRequisitionFormService.createJobRequisition(jobRequisitionFormDTO);
//            return new ResponseEntity<>(savedJobRequisition, HttpStatus.CREATED);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error processing request: " + e.getMessage());
//        }
//    }
@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
public ResponseEntity<?> createJobRequisition(
        @RequestParam("jobRequisitionDTO") String jobRequisitionDTO,
        @RequestParam(value = "jobDescription", required = false) MultipartFile jobDescriptionFile) {
    try {
        // Validate JSON data
        if (!isValidJson(jobRequisitionDTO)) {
            return ResponseEntity.badRequest().body("Invalid JSON Data");
        }

        // Convert JSON string to DTO
        JobRequisitionFormDTO jobRequisitionFormDTO =
                objectMapper.readValue(jobRequisitionDTO, JobRequisitionFormDTO.class);

        // Handle file if provided
        if (jobDescriptionFile != null && !jobDescriptionFile.isEmpty()) {
            jobRequisitionFormDTO.setJobDescriptionFile(jobDescriptionFile.getBytes());
        }

        // Save the job requisition
        JobRequisitionFormDTO savedJobRequisition =
                jobRequisitionFormService.createJobRequisition(jobRequisitionFormDTO);

        return new ResponseEntity<>(savedJobRequisition, HttpStatus.CREATED);
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing request: " + e.getMessage());
    }
}

    // Validate JSON format
    private boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Get job requisition by ID
    @GetMapping("/{jobRequisitionId}")
    public ResponseEntity<JobRequisitionForm> getJobRequisitionById(@PathVariable String jobRequisitionId) {
        JobRequisitionForm jobRequisition = jobRequisitionFormService.getJobRequisitionById(jobRequisitionId);
        return jobRequisition != null ?
                ResponseEntity.ok(jobRequisition) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Get candidates associated with job requisition ID
    @GetMapping("/{jobRequisitionId}/candidates")
    public ResponseEntity<List<CandidateApplicationForm>> getCandidatesByJobRequisitionId(@PathVariable String jobRequisitionId) {
        List<CandidateApplicationForm> candidates = candidateApplicationFormService.getCandidatesByJobRequisitionId(jobRequisitionId);
        return candidates != null && !candidates.isEmpty() ?
                ResponseEntity.ok(candidates) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Get all job requisitions
    @GetMapping
    public ResponseEntity<List<JobRequisitionFormDTO>> getAllJobRequisitionForms() {
        List<JobRequisitionFormDTO> forms = jobRequisitionFormService.getAllJobRequisitionForms();
        return ResponseEntity.ok(forms);
    }

    // Delete job requisition by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobRequisitionForm(@PathVariable String id) {
        jobRequisitionFormService.deleteJobRequisitionForm(id);
        return ResponseEntity.noContent().build();
    }
}
