package com.itolla.test.taskhelper.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(schema = "public")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String title;

    @JsonIgnoreProperties({"password", "issues", "ownProjects", "projects"})
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "userId")
    private User owner;

    @JsonIgnoreProperties({"password", "issues", "ownProjects", "projects"})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "userId")
    private Set<User> users;

    @JsonIgnoreProperties({"description", "user", "project", "labels"})
    @OneToMany(mappedBy = "project")
    private Set<Issue> issues;

    @JsonIgnoreProperties({"project", "issues"})
    @OneToMany(mappedBy = "project")
    private Set<Label> labels;

    public Project(){}
    public Project(String title){
        this.title = title;
    }

    public void setId(Long id){
        this.projectId = id;
    }
    public Long getId(){
        return this.projectId;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

    public void setOwner(User owner){
        // чтобы не зациклиться
        if (this.owner == null? owner == null : this.owner.equals(owner))
            return;
        User oldOwner = this.owner;
        this.owner = owner;

        // сохранение целостности
        if(oldOwner != null)
            oldOwner.removeOwnProject(this);
        if (owner != null)
            owner.addOwnProject(this);
    }

    public User getOwner() {
        return this.owner;
    }

    public Set<User> getUsers(){
        return users;
    }

    public void addUser(User user){

        if (users == null)
            users = new HashSet<>();

        // чтобы не зациклиться
        if ((users.contains(user)) || (user == null))
            return;

        users.add(user);

        // Сохранение целостности
        user.addProject(this);
    }
    public void removeUser(User user){
        // чтобы не зациклиться и не упасть в NullPointer
        if ((users == null) || (!users.contains(user)) || (user == null))
            return;

        users.remove(user);

        // сохранение целостности
        user.removeProject(this);
    }

    public void removeAllUsers(){
        if (users == null)
            return;

        // preventing ConcurrentModificationException
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext();){
            User current = iterator.next();
            iterator.remove();
            current.removeProject(this);
        }
    }

    public Set<Issue> getIssues(){
        return issues;
    }

    public void addIssue(Issue issue){

        if (issues == null)
            issues = new HashSet<>();

        // чтобы не зациклиться
        if((issues.contains(issue)) || (issue == null))
            return;

        issues.add(issue);

        // сохранение целостности
        issue.setProject(this);
    }

    public void removeIssue(Issue issue){
        // чтобы не зациклиться и не упасть в NullPointer
        if((issues == null) || (!issues.contains(issue)) || (issue == null))
            return;

        issues.remove(issue);

        // Сохранение целостности
        issue.setProject(null);
    }

    public void removeAllIssues(){
        if (issues == null)
            return;
        for (Iterator<Issue> iterator = issues.iterator(); iterator.hasNext();){
            Issue current = iterator.next();
            iterator.remove();
            current.setProject(null);
        }
    }
    public Set<Label> getLabels(){
        return this.labels;
    }

    public void addLabel(Label label){

        if (labels == null)
            labels = new HashSet<>();

        // чтобы не зациклиться
        if ((labels.contains(label)) || (label == null))
            return;

        labels.add(label);

        // сохранение целостности
        label.setProject(this);
    }

    public void removeLabel(Label label){
        // чтобы не зациклиться и не упасть в NullPointer
        if ((labels == null) || (!labels.contains(label)) || (label == null))
            return;

        labels.remove(label);

        // сохранение целостности
        label.setProject(null);
    }

    public void removeAllLabels(){
        if (labels == null)
            return;
        for (Iterator<Label> iterator = labels.iterator(); iterator.hasNext();){
            Label current = iterator.next();
            iterator.remove();
            current.setProject(null);
        }
    }
}
