package com.itolla.test.taskhelper.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.itolla.test.taskhelper.model.User;
import com.itolla.test.taskhelper.util.EntityIdResolver;

import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId", resolver = EntityIdResolver.class, scope = UserDto.class)
public class UserDto {
    private Long userId;
    private String username;
    private String password;

    //@JsonIgnoreProperties({"owner", "users", "issues", "labels"})

    private Set<ProjectDto> ownProjects;

    //@JsonIgnoreProperties({"owner", "users", "issues", "labels"})

    private Set<ProjectDto> projects;

    //@JsonIgnoreProperties({"description", "user", "project", "labels"})

    private Set<IssueDto> issues;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //@JsonManagedReference
    public Set<ProjectDto> getOwnProjects() {
        return ownProjects;
    }

    public void setOwnProjects(Set<ProjectDto> ownProjects) {
        this.ownProjects = ownProjects;
    }

    //@JsonManagedReference
    public Set<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDto> projects) {
        this.projects = projects;
    }

    //@JsonManagedReference
    public Set<IssueDto> getIssues() {
        return issues;
    }

    public void setIssues(Set<IssueDto> issues) {
        this.issues = issues;
    }
}
