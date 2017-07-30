package com.itolla.test.taskhelper.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Issue {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Long user_id;
    private Long project_id;

    private Set<Label> labels;

    public Issue(){}
    public Issue(Long id, String title, String description, Long user_id, Long project_id){
        this.id = id;
        this.title = title;
        this.description = description;
        this.user_id = user_id;
        this.project_id = project_id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public Long getUser_id() {
        return this.user_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }
    public Long getProject_id() {
        return this.project_id;
    }

    @ManyToMany(mappedBy = "issues")
    public Set<Label> getLabels(){
        return labels;
    }
    public void setLabels(Set<Label> labels){
        this.labels = labels;
    }
}
