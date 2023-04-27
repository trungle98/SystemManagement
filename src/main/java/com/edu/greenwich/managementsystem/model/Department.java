package com.edu.greenwich.managementsystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer id;

    private String name;

    public Department(int i) {
        this.id = id;

    }

    public Department() {

    }

    //    @OneToMany(mappedBy = "departments")
//    private List<User> users;
    public void Department(){

    }
    public void Department(Integer id){
        this.id = id;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}