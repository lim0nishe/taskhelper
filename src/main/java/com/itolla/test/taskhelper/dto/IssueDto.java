package com.itolla.test.taskhelper.dto;

import com.fasterxml.jackson.annotation.*;
import com.itolla.test.taskhelper.model.Issue;
import com.itolla.test.taskhelper.util.EntityIdResolver;

import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "issueId", resolver = EntityIdResolver.class, scope = IssueDto.class)
public class IssueDto {
    private Long issueId;
    private String title;
    private String description;

    //@JsonIgnoreProperties({"password", "issues", "ownProjects", "projects"})

    private UserDto user;

    //@JsonIgnoreProperties({"owner", "users", "issues", "labels"})

    private ProjectDto project;

    //@JsonIgnoreProperties({"project", "issues"})

    private Set<LabelDto> labels;

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //@JsonBackReference
    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    //@JsonBackReference
    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    //@JsonManagedReference
    public Set<LabelDto> getLabels() {
        return labels;
    }

    public void setLabels(Set<LabelDto> labels) {
        this.labels = labels;
    }
}
