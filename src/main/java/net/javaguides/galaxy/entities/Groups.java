package net.javaguides.galaxy.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer id ;
    private String instructorName;

    @ManyToMany(mappedBy = "groups")
    private List < User > users;

    @OneToMany(mappedBy = "groups")
    private List<Tasks> tasks;

    @OneToMany(mappedBy = "groups")
    private List<Courses> courses;

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public Integer getId() {
        return id;
    }


    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Groups(){

    }
}
