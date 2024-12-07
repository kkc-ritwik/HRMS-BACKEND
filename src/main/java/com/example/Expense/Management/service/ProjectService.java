package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.ProjectDto;
import com.example.Expense.Management.entity.Project;
import com.example.Expense.Management.entity.User;


import java.util.List;

public interface ProjectService {


    Project addProject(ProjectDto projectDto);
    List<Project> getAllProjects();
    Project updateProject(String id, ProjectDto projectDto);
    void deleteProject(String id);
Project toggleProjectStatus(String id, String newStatus);
    int getTotalProjectCount();  // New method to get total project count

}