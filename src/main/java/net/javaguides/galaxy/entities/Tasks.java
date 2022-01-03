package net.javaguides.galaxy.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tasks {
    private String body;
    @ManyToOne
    private  User user ;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(name = "createdAt")
    private String createdAt;

//    @Column(name = "deadLineAt")
    private String deadLineAt;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Tasks(String body, String createdAt, String deadLineAt ,User user) {
        this.body = body;
        this.user = user;
        this.createdAt = createdAt;
        this.deadLineAt = deadLineAt;
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
