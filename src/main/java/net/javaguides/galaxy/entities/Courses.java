package net.javaguides.galaxy.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    private String subject ;

    @OneToMany(mappedBy = "courses")
    private List<Tasks> tasks;

    @OneToMany(mappedBy = "courses")
    private List<Grade> grades;


    @ManyToOne
    private  User user ;

    @ManyToOne
    private Groups groups;

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Courses(String subject, User user,Groups groups) {
        this.subject = subject;
        this.user = user;
        this.groups=groups;
    }
    public Courses() {
    }

    public Integer getId() {
        return id;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
