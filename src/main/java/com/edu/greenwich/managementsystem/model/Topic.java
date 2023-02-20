package com.edu.greenwich.managementsystem.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Integer id;

    private String name;

    private Date closure;

    private Date finalClosure;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getClosure() {
        return closure;
    }

    public void setClosure(Date closure) {
        this.closure = closure;
    }

    public Date getFinalClosure() {
        return finalClosure;
    }

    public void setFinalClosure(Date finalClosure) {
        this.finalClosure = finalClosure;
    }


    public Topic() {

    }
}
