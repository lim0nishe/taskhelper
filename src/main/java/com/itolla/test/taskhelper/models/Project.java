package com.itolla.test.taskhelper.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long owner_id;

    private Set<User> users;

    public Project(){}
    public Project(Long id, String title, Long owner_id){
        this.id = id;
        this.title = title;
        this.owner_id = owner_id;
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
    public String getTitle(){
        return this.title;
    }

    public void setOwner_id(Long owner_id){
        this.owner_id = owner_id;
    }
    public Long getOwner_id() {
        return this.owner_id;
    }

    @ManyToMany(mappedBy = "projects")
    public Set<User> getUsers(){
        return users;
    }
    public void setUsers(Set<User> users){
        this.users = users;
    }
}
