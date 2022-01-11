package net.javaguides.galaxy.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Caio Fernando
 */

 @Entity
 @Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(nullable = false)
    @NotEmpty()
    private String name;

    @Column(nullable = false,unique = true)
    @NotEmpty
    @Email(message = "{erros.invalid_email}")
    private String email;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 4)
    private String password;

    @Column(nullable = false)
    @Size(min = 10)
    private  String phoneNumber ;

    @Column(nullable = false)
    private  String gender ;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "user_role",
        joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")}
    )
    private List <Role> roles;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_group",
            joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name="GROUP_ID",referencedColumnName = "ID")}
    )
    private  List<Groups> groups ;

    @OneToMany(mappedBy = "user")
    private List<Courses> courses;


    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public List<TaskDelivery> getTaskDeliveries() {
        return taskDeliveries;
    }

    public void setTaskDeliveries(List<TaskDelivery> taskDeliveries) {
        this.taskDeliveries = taskDeliveries;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Tasks> tasks;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<TaskDelivery> taskDeliveries;

    public List<Tasks> getTasks() {
        return tasks;
    }

      @OneToMany(mappedBy = "user")
    private List<Grade> grades;

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public User(String firstName, String email, String password, String phoneNumber, String gender) {
        this.name=firstName;
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber ;
        this.gender=gender ;
    }

    public User() {

    }
    
     @OneToMany(mappedBy = "user")
    private List<Message> messages;

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
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return List<Role> return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
