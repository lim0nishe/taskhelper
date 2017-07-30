package com.itolla.test.taskhelper.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    private Set<Project> projects;

    public User(){}
    public User(Long id, String username, String password){
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    @ManyToMany(mappedBy = "users")
    public Set<Project> getProjects(){
        return projects;
    }
    public void setProjects(Set<Project> projects){
        this.projects = projects;
    }
}