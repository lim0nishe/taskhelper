package com.itolla.test.taskhelper.Services;

import com.itolla.test.taskhelper.models.Project;

import java.util.List;

public interface ProjectService {
    Project getProjectById(Long id);
    List<Project> getAllProjects();
    Project save(Project project);
    Project update(Project project);
}
