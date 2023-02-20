package com.edu.greenwich.managementsystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Idea")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idea_id")
    private Integer id;

    @Column(length = 60)
    private String brief;

    @Column(length = 500)
    private String content;

    private String author;

    private String fileLocation;

    private String tag;

    @OneToMany(mappedBy = "idea")
    private List<Reaction> reactions;

    @OneToMany(mappedBy = "idea")
    private List<Comment> comments;

    public Idea() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return brief;
    }

    public void setName(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
