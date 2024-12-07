package com.example.Recruitment.controller.form;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.form.CandidateApplicationFormDTO;
import com.example.Recruitment.model.form.CandidateApplicationForm;
import com.example.Recruitment.model.form.Round;
import com.example.Recruitment.service.form.CandidateApplicationFormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/application-Form")
@Validated
public class CandidateApplicationFormController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CandidateApplicationFormService candidateApplicationFormService;

    @GetMapping
    public ResponseEntity<List<CandidateApplicationFormDTO>> getAllCandidateForms() {
        List<CandidateApplicationFormDTO> forms = candidateApplicationFormService.getAllCandidateForms();
        return ResponseEntity.ok(forms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateApplicationFormDTO> getCandidateFormById(@PathVariable String id) {
        CandidateApplicationFormDTO form = candidateApplicationFormService.getCandidateFormById(id);
        return ResponseEntity.ok(form);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> applyForJob(
            @RequestParam String candidateDTO,
            @RequestParam MultipartFile resume) {
        if (!isValidJson(candidateDTO)) {
            return ResponseEntity.badRequest().body("Invalid JSON data");
        }
        try {
            CandidateApplicationFormDTO candidateApplicationFormDTO =
                    objectMapper.readValue(candidateDTO, CandidateApplicationFormDTO.class);
            candidateApplicationFormDTO.setResume(resume.getBytes());
            CandidateApplicationFormDTO savedForm =
                    candidateApplicationFormService.applyForJob(candidateApplicationFormDTO);
            return new ResponseEntity<>(savedForm, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing the request: " + e.getMessage());
        }
    }

//    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> applyForJob(
//            @RequestPart String candidateDTO, // Use @RequestPart here
//            @RequestPart MultipartFile resume) { // Use @RequestPart here
//        if (!isValidJson(candidateDTO)) {
//            return ResponseEntity.badRequest().body("Invalid JSON data");
//        }
//        try {
//            CandidateApplicationFormDTO candidateApplicationFormDTO =
//                    objectMapper.readValue(candidateDTO, CandidateApplicationFormDTO.class);
//            candidateApplicationFormDTO.setResume(resume.getBytes());
//            CandidateApplicationFormDTO savedForm =
//                    candidateApplicationFormService.applyForJob(candidateApplicationFormDTO);
//            return new ResponseEntity<>(savedForm, HttpStatus.CREATED);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error processing the request: " + e.getMessage());
//        }
//    }
//


    private boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateForm(@PathVariable String id) {
        candidateApplicationFormService.deleteCandidateForm(id);
    }

    @GetMapping("/job/{jobRequisitionId}")
    public ResponseEntity<List<CandidateApplicationForm>> getCandidatesByJobRequisitionId(
            @PathVariable String jobRequisitionId) {
        List<CandidateApplicationForm> candidates =
                candidateApplicationFormService.getCandidatesByJobRequisitionId(jobRequisitionId);
        return ResponseEntity.ok(candidates);
    }

    @PutMapping("/{id}/rounds")
    public ResponseEntity<CandidateApplicationFormDTO> updateCandidateRounds(
            @PathVariable String id,
            @RequestBody Map<String, Round> rounds) {
        Round round1 = rounds.getOrDefault("round1", new Round("-", "-"));
        Round round2 = rounds.getOrDefault("round2", new Round("-", "-"));
        Round round3 = rounds.getOrDefault("round3", new Round("-", "-"));

        CandidateApplicationFormDTO updatedForm =
                candidateApplicationFormService.updateCandidateRounds(id, round1, round2, round3);

        return ResponseEntity.ok(updatedForm);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
