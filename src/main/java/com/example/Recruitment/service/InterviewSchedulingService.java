package com.example.Recruitment.service;

import com.example.Recruitment.dto.InterviewSchedulingDTO;
import java.util.List;

public interface InterviewSchedulingService {
    List<InterviewSchedulingDTO> getAllInterviews();
    InterviewSchedulingDTO getInterviewById(String id);
    InterviewSchedulingDTO createInterview(InterviewSchedulingDTO interviewSchedulingDTO);
    InterviewSchedulingDTO updateInterview(String id, InterviewSchedulingDTO interviewSchedulingDTO);
    void deleteInterview(String id);
    void sendInterviewEmail(String id);
}