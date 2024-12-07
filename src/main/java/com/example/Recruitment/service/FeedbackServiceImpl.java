package com.example.Recruitment.service;

import com.example.Recruitment.dto.FeedbackDTO;
import com.example.Recruitment.model.Feedback;
import com.example.Recruitment.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback saveFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setCandidateId(feedbackDTO.getCandidateId());
        feedback.setCandidateName(feedbackDTO.getCandidateName());
        feedback.setRecruiter(feedbackDTO.getRecruiter());
        feedback.setFeedback(feedbackDTO.getFeedback());
        feedback.setStage(feedbackDTO.getStage());    // Added this
        feedback.setStatus(feedbackDTO.getStatus());  // Added this
        feedback.setCreatedAt(new Date());

        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getFeedbacksByCandidateId(String candidateId) {
        return feedbackRepository.findByCandidateIdOrderByCreatedAtDesc(candidateId);
    }
}
