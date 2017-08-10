package com.itolla.test.taskhelper.Services;

import com.itolla.test.taskhelper.Repositories.ProjectRepository;
import com.itolla.test.taskhelper.Repositories.ProjectRepositoryCustom;
import com.itolla.test.taskhelper.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findOne(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project save(Project project) {
        return ((ProjectRepositoryCustom)projectRepository).save(project);
    }

    @Override
    public Project update(Project project) {
        return ((ProjectRepositoryCustom)projectRepository).save(project);
    }
}
