package com.itolla.test.taskhelper.service;

import com.itolla.test.taskhelper.model.Project;

import java.util.List;

public interface ProjectService {
    Project getProjectById(Long id);
    List<Project> getAllProjects();
    Project save(Project project);
    Project update(Project project);
}
