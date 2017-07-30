package com.itolla.test.taskhelper.controllers;

import com.itolla.test.taskhelper.Services.ProjectService;
import com.itolla.test.taskhelper.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Project> getProjects(){
        return projectService.getAllProjects();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable("id") Long id){
        return projectService.getProjectById(id);
    }
}
