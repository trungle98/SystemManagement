package com.edu.greenwich.managementsystem.model;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "Idea")
@Builder
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idea_id")
    private Long id;

    private String brief;
    private String content;

    private String author;

    private String fileLocation;

    private long topicId;

    private long categoryId;

    public Idea() {

    }

    public Idea(Long id, String brief, String content, String author, String fileLocation, long topicId, long categoryId) {
        this.id = id;
        this.brief = brief;
        this.content = content;
        this.author = author;
        this.fileLocation = fileLocation;
        this.topicId = topicId;
        this.categoryId = categoryId;
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

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
