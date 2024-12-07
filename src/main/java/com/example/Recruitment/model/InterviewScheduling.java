package com.example.Recruitment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "interview-scheduling")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewScheduling {
    @Id
    private String id = UUID.randomUUID().toString();
    private String interviewTitle;
    private String interviewId;
    private String assignedTo;
    private List<String> candidates = new ArrayList<>();

    private String interviewType;
    private String location;
    private String interviewerName;
    private String interviewerRole;
    private String duration;
    private List<String> availableTimeSlots; // For predefined slots like "MondayEarly Morning (1am-9am)"
    private String customSlotDate;
    private String customSlotStartTime;
    private String customSlotEndTime;

    // Contact fields
    private String from;
    private String to;
    private String subject;
    private String message;
    private String contactVia;
    private String language;
    private String emailTemplate;
}