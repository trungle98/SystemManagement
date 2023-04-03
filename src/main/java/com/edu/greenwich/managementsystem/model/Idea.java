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
    private int id;

    private String brief;
    private String content;

    private String author;

    private String fileLocation;

    private long topicId;

    private long categoryId;

    private int views;

    public Idea(int id, String brief, String content, String author, String fileLocation, long topicId, long categoryId, int views) {
        this.id = id;
        this.brief = brief;
        this.content = content;
        this.author = author;
        this.fileLocation = fileLocation;
        this.topicId = topicId;
        this.categoryId = categoryId;
        this.views = views;
    }

    public Idea() {

    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getViews() {
        return views;
    }

    public Idea(int id, String brief, String content, String author, String fileLocation, long topicId, long categoryId) {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
