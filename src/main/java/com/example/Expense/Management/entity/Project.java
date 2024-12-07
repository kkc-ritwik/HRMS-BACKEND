package com.example.Expense.Management.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "projects")
@Data
public class Project {
    @Id
    private String id;
    private String code;
    private String name;
    private String clientName;
    private String teamLead;
    private String projectManager;
    private List<String> teamMembers;
    private String timesheetApprovalType;
    private String expenseApprovalType;
    private String description;
    private String duration;
    private String estimatedCost;
    private String status;
    private String startDate;
    private String dueDate;
}