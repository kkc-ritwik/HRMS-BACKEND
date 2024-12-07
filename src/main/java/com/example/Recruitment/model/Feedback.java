package com.example.Recruitment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "candidate-feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    private String id;
    private String candidateId;
    private String candidateName;
    private String recruiter;
    private String feedback;
    private String stage;   // Added field
    private String status;  // Added field
    private Date createdAt;
}