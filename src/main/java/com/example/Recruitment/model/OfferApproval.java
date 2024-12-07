package com.example.Recruitment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "offer_approvals")
public class OfferApproval {
    @Id
    private String id;
    private String candidateId;
    private String candidateName;
    private String currentDesignation;
    private String offeredDesignation;
    private double currentCTC;
    private double offeredCTC;
    private double totalExperience;
    private String jobLocation;
    private String interviewedBy;
    private String interviewFeedback;
    private String status; // PENDING, APPROVED, REJECTED
    private String createdBy;
    private String updatedBy;
    private Date createdAt;
    private Date updatedAt;
}