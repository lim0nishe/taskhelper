package com.itolla.test.taskhelper.dto;

import com.fasterxml.jackson.annotation.*;
import com.itolla.test.taskhelper.model.Project;
import com.itolla.test.taskhelper.util.EntityIdResolver;

import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "projectId", resolver = EntityIdResolver.class, scope = ProjectDto.class)
public class ProjectDto {
    private Long projectId;
    private String title;

    //@JsonIgnoreProperties({"password", "issues", "ownProjects", "projects"})

    private UserDto owner;

    //@JsonIgnoreProperties({"password", "issues", "ownProjects", "projects"})

    private Set<UserDto> users;

    //@JsonIgnoreProperties({"description", "user", "project", "labels"})

    private Set<IssueDto> issues;

    //@JsonIgnoreProperties({"project", "issues"})

    private Set<LabelDto> labels;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //@JsonBackReference
    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    //@JsonManagedReference
    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }

   //@JsonManagedReference
    public Set<IssueDto> getIssues() {
        return issues;
    }

    public void setIssues(Set<IssueDto> issues) {
        this.issues = issues;
    }

    //@JsonManagedReference
    public Set<LabelDto> getLabels() {
        return labels;
    }

    public void setLabels(Set<LabelDto> labels) {
        this.labels = labels;
    }
}
