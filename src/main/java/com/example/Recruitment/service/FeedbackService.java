package com.example.Recruitment.service;

import com.example.Recruitment.dto.FeedbackDTO;
import com.example.Recruitment.model.Feedback;
import java.util.List;

public interface FeedbackService {
    Feedback saveFeedback(FeedbackDTO feedbackDTO);
    List<Feedback> getFeedbacksByCandidateId(String candidateId);
}