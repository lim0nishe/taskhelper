package com.itolla.test.taskhelper.controller;

import com.itolla.test.taskhelper.dto.IssueDto;
import com.itolla.test.taskhelper.dto.LabelDto;
import com.itolla.test.taskhelper.dto.ProjectDto;
import com.itolla.test.taskhelper.model.Issue;
import com.itolla.test.taskhelper.model.Label;
import com.itolla.test.taskhelper.repository.IssueRepository;
import com.itolla.test.taskhelper.repository.LabelRepository;
import com.itolla.test.taskhelper.repository.ProjectRepository;
import com.itolla.test.taskhelper.model.Project;
import com.itolla.test.taskhelper.util.ProjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public Page<ProjectDto> getProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(project -> modelMapper.map(project, ProjectDto.class));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ProjectDto createProject(@RequestBody ProjectDto projectDto){
        Project project = modelMapper.map(projectDto, Project.class);
        projectRepository.save(project);

        return modelMapper.map(project, ProjectDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProjectDto getProject(@PathVariable("id") Long id){
        return modelMapper.map(projectRepository.findOne(id), ProjectDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ProjectDto updateProject(@PathVariable("id") Long id, @RequestBody ProjectDto projectDto){
        Project project = projectRepository.findOne(id);
        if (project == null) throw new ProjectNotFoundException();

        Project updatedProject = modelMapper.map(projectDto, Project.class);
        updatedProject.setId(id);
        projectRepository.save(updatedProject);
        return modelMapper.map(updatedProject, ProjectDto.class);
    }

    @RequestMapping(value = "/{id}/labels", method = RequestMethod.GET)
    public Page<LabelDto> getProjectLabels(@PathVariable("id") Long id, Pageable pageable){

        // можно убрать, если примлимо, что будет падать внутри запроса
        Project project = projectRepository.findOne(id);

        if (project == null) throw new ProjectNotFoundException();
        return labelRepository.findAllByProjectProjectId(id, pageable)
                .map(label -> modelMapper.map(label, LabelDto.class));
    }

    @RequestMapping(value = "/{id}/labels", method = RequestMethod.POST)
    public LabelDto createLabelForProject(@PathVariable("id") Long projectId, @RequestBody LabelDto labelDto){
        Project project = projectRepository.findOne(projectId);
        if (project == null) throw new ProjectNotFoundException();

        Label label = modelMapper.map(labelDto, Label.class);
        label.setProject(project);

        labelRepository.save(label);
        return modelMapper.map(label, LabelDto.class);
    }

    @RequestMapping(value = "/{id}/issues", method = RequestMethod.GET)
    public Page<IssueDto> getProjectIssues(@PathVariable("id") Long projectId, Pageable pageable){

        // можно убрать, если примлимо, что будет падать внутри запроса
        Project project = projectRepository.findOne(projectId);

        if (project == null) throw new ProjectNotFoundException();
        return issueRepository.findAllByProjectProjectId(projectId, pageable)
                .map(issue -> modelMapper.map(issue, IssueDto.class));
    }

    @RequestMapping(value = "/{id}/issues", method = RequestMethod.POST)
    public IssueDto createIssueForProject(@PathVariable("id") Long projectId, @RequestBody IssueDto issueDto){
        Project project = projectRepository.findOne(projectId);
        if (project == null) throw new ProjectNotFoundException();

        Issue issue = modelMapper.map(issueDto, Issue.class);
        issue.setProject(project);
        issueRepository.save(issue);
        return modelMapper.map(issue, IssueDto.class);
    }
}
