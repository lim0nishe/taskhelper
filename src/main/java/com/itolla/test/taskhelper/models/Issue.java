package com.itolla.test.taskhelper.models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    private String title;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private Project project;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "labelId")
    private Set<Label> labels;

    public Issue(){}
    public Issue(String title, String description){
        this.title = title;
        this.description = description;
    }

    public void setId(Long id) {
        this.issueId = id;
    }
    public Long getId() {
        return this.issueId;
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

     // Вылетала ошибка detached entity passed to persist,
     // скорее всего возникала из-за нарушения целостности.
     // Переделал сеттеры

    public void setUser(User user) {
        // чтобы не зациклиться
        if (this.user == null? user == null : this.user.equals(user))
            return;
        User oldUser = this.user;
        this.user = user;

        // сохранение целостности
        if(oldUser != null)
            oldUser.removeIssue(this);
        if (user != null)
            user.addIssue(this);
    }

    public User getUser() {
        return this.user;
    }

    public void setProject(Project project) {
        // чтобы не зациклиться
        if (this.project == null? project == null : this.project.equals(project))
            return;
        Project oldProject = this.project;
        this.project = project;

        // сохранение целостности
        if (oldProject != null)
            oldProject.removeIssue(this);
        if (project != null)
            project.addIssue(this);
    }

    public Project getProject() {
        return this.project;
    }

    public Set<Label> getLabels(){
        return labels;
    }

    public void addLabel(Label label){

        if (labels == null)
            labels = new HashSet<>();

        // чтобы не зациклиться, проверка на null потому что некоторые сеты вроде могут содержать null
        if((labels.contains(label)) || (label == null))
            return;
        labels.add(label);

        // сохранение целостности
        label.addIssue(this);
    }
    public void removeLabel(Label label){

        // чтобы не зациклиться и не упасть в NullPointer
        if((labels == null) || (!labels.contains(label)) || (label == null))
            return;
        labels.remove(label);

        // соранение целостности
        label.removeIssue(this);
    }
}
