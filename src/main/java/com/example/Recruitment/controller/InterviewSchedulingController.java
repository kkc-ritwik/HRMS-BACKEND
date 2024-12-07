package com.example.Recruitment.controller;

import com.example.Recruitment.dto.InterviewSchedulingDTO;
import com.example.Recruitment.service.InterviewSchedulingService;
import com.example.Recruitment.config.ApiVersionConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/interviews")
@CrossOrigin("*")
public class InterviewSchedulingController {

    @Autowired
    private InterviewSchedulingService service;

    @GetMapping
    public ResponseEntity<List<InterviewSchedulingDTO>> getAllInterviews() {
        List<InterviewSchedulingDTO> interviews = service.getAllInterviews();
        return new ResponseEntity<>(interviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewSchedulingDTO> getInterviewById(@PathVariable String id) {
        InterviewSchedulingDTO interview = service.getInterviewById(id);
        return new ResponseEntity<>(interview, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InterviewSchedulingDTO> createInterview(@Valid @RequestBody InterviewSchedulingDTO interviewSchedulingDTO) {
        InterviewSchedulingDTO createdInterview = service.createInterview(interviewSchedulingDTO);
        return new ResponseEntity<>(createdInterview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewSchedulingDTO> updateInterview(@PathVariable String id, @Valid @RequestBody InterviewSchedulingDTO interviewSchedulingDTO) {
        InterviewSchedulingDTO updatedInterview = service.updateInterview(id, interviewSchedulingDTO);
        return new ResponseEntity<>(updatedInterview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(@PathVariable String id) {
        service.deleteInterview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/send-email")
    public ResponseEntity<String> sendInterviewEmail(@PathVariable String id) {
        service.sendInterviewEmail(id);
        return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
    }
}
