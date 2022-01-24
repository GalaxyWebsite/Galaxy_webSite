package net.javaguides.galaxy.entities;

import net.javaguides.galaxy.repositories.GradeRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String createdAt;
    private String deadLineAt;
    private String title;
    @Column(length = 2000000)
    private String body;

     @OneToMany(mappedBy = "messageTask")
       private List<Message> messages;

    @ManyToOne
    private  User user ;

    @OneToMany(mappedBy = "tasks")
    private List<Grade> grade;

    @OneToMany(mappedBy = "userTasks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<TaskDelivery> taskDelivery;

    @ManyToOne
    private Courses courses;

    @ManyToOne
    private Groups groups;

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public String getBody() {
        return body;
    }
    public Grade getgradee(GradeRepository gradeRepository,Integer userId,Integer taskId) {
        return gradeRepository.findByUserIdAndTasksId(userId,taskId);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Tasks(String title,String body, String createdAt, String deadLineAt ,User user,Courses courses,Groups groups) {
        this.body = body;
        this.user = user;
        this.createdAt = createdAt;
        this.deadLineAt = deadLineAt;
        this.title=title;
        this.courses=courses;
        this.groups=groups;
    }
    public Tasks(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Grade> getGrade() {
        return grade;
    }

    public void setGrade(List<Grade> grade) {
        this.grade = grade;
    }

    public List<TaskDelivery> getTaskDelivery() {
        return taskDelivery;
    }

    public void setTaskDelivery(List<TaskDelivery> taskDelivery) {
        this.taskDelivery = taskDelivery;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeadLineAt() {
        return deadLineAt;
    }

    public void setDeadLineAt(String deadLineAt) {
        this.deadLineAt = deadLineAt;
    }

    public Integer getId() {
        return id;
    }

}
