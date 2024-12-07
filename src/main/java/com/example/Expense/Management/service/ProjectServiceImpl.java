package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.ProjectDto;
import com.example.Expense.Management.entity.Project;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.repository.ProjectRepository;
import com.example.Expense.Management.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Project addProject(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(String id, ProjectDto projectDto) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        modelMapper.map(projectDto, existingProject); // Map projectDto properties to existingProject
        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project toggleProjectStatus(String id, String newStatus) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        existingProject.setStatus(newStatus);
        return projectRepository.save(existingProject);
    }

    @Override
    public int getTotalProjectCount() {
        return (int) projectRepository.count();
    }




}
