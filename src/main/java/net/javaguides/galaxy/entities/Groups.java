package net.javaguides.galaxy.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer id ;
    private String instructorName;

    @OneToMany(mappedBy = "groups")
    private List<User> users;

    public Groups(){

    }
}
