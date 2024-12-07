package com.example.Expense.Management.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
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