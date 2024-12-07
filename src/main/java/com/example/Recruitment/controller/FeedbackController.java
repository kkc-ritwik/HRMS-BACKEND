package com.example.Recruitment.controller;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.FeedbackDTO;
import com.example.Recruitment.model.Feedback;
import com.example.Recruitment.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/feedback")
@CrossOrigin("*")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public ResponseEntity<?> addFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        try {
            Feedback feedback = feedbackService.saveFeedback(feedbackDTO);
            return new ResponseEntity<>(feedback, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save feedback: " + e.getMessage());
        }
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<?> getFeedbacksByCandidateId(@PathVariable String candidateId) {
        try {
            List<Feedback> feedbacks = feedbackService.getFeedbacksByCandidateId(candidateId);
            return ResponseEntity.ok(feedbacks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch feedbacks: " + e.getMessage());
        }
    }
}
