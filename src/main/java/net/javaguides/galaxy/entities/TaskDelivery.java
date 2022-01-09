package net.javaguides.galaxy.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class TaskDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    private String comment;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    private  User student ;

    @OneToMany(mappedBy = "taskDelivery")
    private List<Grade> grade;

    @ManyToOne
    private Tasks userTasks;

    public TaskDelivery(String body, String comment , User student, Tasks userTasks) {
        this.body = body;
        this.comment = comment;
        this.student=student ;
        this.userTasks=userTasks;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public List<Grade> getGrade() {
        return grade;
    }

    public void setGrade(List<Grade> grade) {
        this.grade = grade;
    }

    public Tasks getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(Tasks userTasks) {
        this.userTasks = userTasks;
    }

    public TaskDelivery(){

    }
    public Integer getId() {
        return id;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
