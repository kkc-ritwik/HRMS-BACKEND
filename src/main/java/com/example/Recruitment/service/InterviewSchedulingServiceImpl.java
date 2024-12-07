package com.example.Recruitment.service;

import com.example.Recruitment.dto.InterviewSchedulingDTO;
import com.example.Recruitment.exception.ResourceNotFoundException;
import com.example.Recruitment.model.InterviewScheduling;
import com.example.Recruitment.repository.InterviewSchedulingRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewSchedulingServiceImpl implements InterviewSchedulingService {
    private static final Logger logger = LoggerFactory.getLogger(InterviewSchedulingServiceImpl.class);

    @Autowired
    private InterviewSchedulingRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public List<InterviewSchedulingDTO> getAllInterviews() {
        return repository.findAll().stream()
                .map(interview -> modelMapper.map(interview, InterviewSchedulingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public InterviewSchedulingDTO getInterviewById(String id) {
        InterviewScheduling interview = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id " + id));
        return modelMapper.map(interview, InterviewSchedulingDTO.class);
    }

    @Override
    public InterviewSchedulingDTO createInterview(InterviewSchedulingDTO interviewSchedulingDTO) {
        InterviewScheduling interview = modelMapper.map(interviewSchedulingDTO, InterviewScheduling.class);
        InterviewScheduling savedInterview = repository.save(interview);
        return modelMapper.map(savedInterview, InterviewSchedulingDTO.class);
    }

    @Override
    public InterviewSchedulingDTO updateInterview(String id, InterviewSchedulingDTO interviewSchedulingDTO) {
        InterviewScheduling existingInterview = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id " + id));

        modelMapper.map(interviewSchedulingDTO, existingInterview);
        existingInterview.setId(id);
        InterviewScheduling updatedInterview = repository.save(existingInterview);
        return modelMapper.map(updatedInterview, InterviewSchedulingDTO.class);
    }

    @Override
    public void deleteInterview(String id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id " + id));
        repository.deleteById(id);
    }

    @Override
    public void sendInterviewEmail(String id) {
        InterviewScheduling interview = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id " + id));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(interview.getFrom() != null ? interview.getFrom() : fromEmail);
        message.setTo(interview.getTo().split(","));
        message.setSubject(interview.getSubject());
        message.setText(buildEmailContent(interview));

        try {
            mailSender.send(message);
            logger.info("Interview email sent successfully to {}", interview.getTo());
        } catch (Exception e) {
            logger.error("Failed to send interview email to {}", interview.getTo(), e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    private String buildEmailContent(InterviewScheduling interview) {
        StringBuilder content = new StringBuilder();
        content.append("Dear Candidate,\n\n");
        content.append("You have been scheduled for an interview.\n\n");
        content.append("Interview Details:\n");
        content.append("Title: ").append(interview.getInterviewTitle()).append("\n");
        content.append("Type: ").append(interview.getInterviewType()).append("\n");
        content.append("Location: ").append(interview.getLocation()).append("\n");
        content.append("Interviewer: ").append(interview.getInterviewerName()).append("\n");
        content.append("Duration: ").append(interview.getDuration()).append("\n");

        if (interview.getAvailableTimeSlots() != null && !interview.getAvailableTimeSlots().isEmpty()) {
            content.append("\nAvailable Time Slots:\n");
            interview.getAvailableTimeSlots().forEach(slot -> content.append("- ").append(slot).append("\n"));
        }

        if (interview.getCustomSlotDate() != null) {
            content.append("\nCustom Time Slot:\n");
            content.append("Date: ").append(interview.getCustomSlotDate()).append("\n");
            content.append("Time: ").append(interview.getCustomSlotStartTime())
                    .append(" - ").append(interview.getCustomSlotEndTime()).append("\n");
        }

        if (interview.getMessage() != null && !interview.getMessage().isEmpty()) {
            content.append("\nAdditional Information:\n");
            content.append(interview.getMessage());
        }

        content.append("\n\nBest regards,\n");
        content.append(interview.getAssignedTo());

        return content.toString();
    }
}