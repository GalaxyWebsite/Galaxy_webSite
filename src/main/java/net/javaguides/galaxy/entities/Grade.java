package net.javaguides.galaxy.entities;

import javax.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    public Integer content;

    @Column(nullable = false)
    public Integer outOf;

    @ManyToOne
    private User user;

    @ManyToOne
    private Courses courses;

    @ManyToOne
    private Tasks tasks;

    @ManyToOne
    private TaskDelivery taskDelivery;

    public Grade(){

    }
    public Grade(Integer content,Integer outOf, User user, Tasks tasks,Courses courses) {
        this.content = content;
        this.outOf=outOf;
        this.user = user;
        this.tasks = tasks;
        this.courses=courses;

    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    public TaskDelivery getTaskDelivery() {
        return taskDelivery;
    }

    public void setTaskDelivery(TaskDelivery taskDelivery) {
        this.taskDelivery = taskDelivery;
    }

    public Integer getOutOf() {
        return outOf;
    }

    public void setOutOf(Integer outOf) {
        this.outOf = outOf;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the content
     */
    public Integer getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(Integer content) {
        this.content = content;
    }
}
