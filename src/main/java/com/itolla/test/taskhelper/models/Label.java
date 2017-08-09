package com.itolla.test.taskhelper.models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labelId;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private Project project;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "labels")
    private Set<Issue> issues;

    public Label(){}
    public Label(String title){
        this.title = title;
    }

    public void setId(Long id){
        this.labelId = id;
    }
    public Long getId(){
        return this.labelId;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public void setProject(Project project) {
        // чтобы не зациклиться
        if (this.project == null? project == null : this.project.equals(project))
            return;
        Project oldProject = this.project;
        this.project = project;

        // сохранение целостности
        if (oldProject != null)
            oldProject.removeLabel(this);
        if (project != null)
            project.addLabel(this);
    }

    public Project getProject() {
        return this.project;
    }

    public Set<Issue> getIssues(){
        return issues;
    }

    public void addIssue(Issue issue){

        if (issues == null)
            issues = new HashSet<>();

        // чтобы не зациклиться
        if ((issues.contains(issue)) || (issue == null))
            return;

        issues.add(issue);

        // сохранение целостности
        issue.addLabel(this);
    }
    public void removeIssue(Issue issue){
        // Чтобы не зациклиться
        if ((issues == null) || (!issues.contains(issue)) || (issue == null))
            return;

        issues.remove(issue);

        // сохранение целостности
        issue.removeLabel(this);
    }
}
