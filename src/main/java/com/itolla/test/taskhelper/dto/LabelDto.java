package com.itolla.test.taskhelper.dto;

import com.fasterxml.jackson.annotation.*;
import com.itolla.test.taskhelper.model.Label;
import com.itolla.test.taskhelper.util.EntityIdResolver;

import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "labelId", resolver = EntityIdResolver.class, scope = LabelDto.class)
public class LabelDto {
    private Long labelId;
    private String title;

    //@JsonIgnoreProperties({"owner", "users", "issues", "labels"})

    private ProjectDto project;

    //@JsonIgnoreProperties({"description", "user", "project", "labels"})

    private Set<IssueDto> issues;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //@JsonBackReference
    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    //@JsonManagedReference
    public Set<IssueDto> getIssues() {
        return issues;
    }

    public void setIssues(Set<IssueDto> issues) {
        this.issues = issues;
    }
}
