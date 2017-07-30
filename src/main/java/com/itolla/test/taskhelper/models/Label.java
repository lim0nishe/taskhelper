package com.itolla.test.taskhelper.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Label {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long project_id;

    private Set<Issue> issues;

    public Label(){}
    public Label(Long id, String title, Long project_id){
        this.id = id;
        this.title = title;
        this.project_id = project_id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }
    public Long getProject_id() {
        return this.project_id;
    }

    @ManyToMany(mappedBy = "labels")
    public Set<Issue> getIssues(){
        return issues;
    }
    public void setIssues(Set<Issue> issues){
        this.issues = issues;
    }
}
