package com.itolla.test.taskhelper.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String username;
    private String password;

    @JsonIgnoreProperties({"description", "user", "project", "labels"})
    @OneToMany(mappedBy = "user")
    private Set<Issue> issues;

    @JsonIgnoreProperties({"owner", "users", "issues", "labels"})
    @OneToMany(mappedBy = "owner")
    private Set<Project> ownProjects;

    @JsonIgnoreProperties({"owner", "users", "issues", "labels"})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            mappedBy = "users")
    private Set<Project> projects;

    public User(){}
    public User(String username, String password){
        this.password = password;
        this.username = username;
    }

    public void setId(Long id){
        this.userId = id;
    }
    public Long getId(){
        return this.userId;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword(){
        return this.password;
    }

    public Set<Project> getProjects(){
        return projects;
    }

    public void addProject(Project project){

        if (projects == null)
            projects = new HashSet<>();

        // чтобы не зациклиться
        if ((projects.contains(project)) || (project == null))
            return;

        projects.add(project);

        // сохранение целостности
        project.addUser(this);
    }

    public void removeProject(Project project){
        // чтобы не зациклиться и не упасть в NullPointer
        if ((projects == null) || (!projects.contains(project)) || (project == null))
            return;

        projects.remove(project);

        // сохранение целостности
        project.removeUser(this);
    }

    public void addIssue(Issue issue){

        if (issues == null)
            issues = new HashSet<>();

        // чтобы не зациклиться
        if ((issues.contains(issue)) || (issue == null))
            return;

        issues.add(issue);

        // сохранение целостности
        issue.setUser(this);
    }

    public void removeIssue(Issue issue){
        // чтобы не зациклиться и не упасть в NulPointer
        if ((issues == null) || (!issues.contains(issue)) || (issue == null))
            return;

        issues.remove(issue);

        // сохранение целостности
        issue.setUser(null);
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void addOwnProject(Project ownProject) {

        if (ownProjects == null)
            ownProjects = new HashSet<>();

        // чтобы не зациклиться
        if ((ownProjects.contains(ownProject)) || (ownProject == null))
            return;

        ownProjects.add(ownProject);

        // Сохранение целостности
        ownProject.setOwner(this);
    }

    public void removeOwnProject(Project ownProject){
        // чтобы не зациклиться и не упасть в NullPointer
        if ((ownProjects == null) || (!ownProjects.contains(ownProject)) || (ownProject == null))
            return;

        ownProjects.remove(ownProject);

        // Сохранение целостности
        ownProject.setOwner(null);
    }

    public Set<Project> getOwnProjects() {
        return ownProjects;
    }
}