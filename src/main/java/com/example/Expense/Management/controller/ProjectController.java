package com.example.Expense.Management.controller;

import com.example.Expense.Management.config.ApiVersionConfig;
import com.example.Expense.Management.dto.ProjectDto;
import com.example.Expense.Management.entity.Project;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.service.AuthService.UserService;
import com.example.Expense.Management.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/projects")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody ProjectDto projectDto) {
        Project project = projectService.addProject(projectDto);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody ProjectDto projectDto) {
        Project project = projectService.updateProject(id, projectDto);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<Project> toggleProjectStatus(@PathVariable String id, @RequestBody Map<String, String> statusMap) {
        String newStatus = statusMap.get("status");
        Project project = projectService.toggleProjectStatus(id, newStatus);
        return ResponseEntity.ok(project);
    }

    // New endpoint to get the total number of projects
    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalProjectCount() {
        int count = projectService.getTotalProjectCount();
        return ResponseEntity.ok(count);
    }


}