//package com.edu.greenwich.managementsystem.dto.request;
//
//import com.edu.greenwich.managementsystem.model.Idea;
//import org.springframework.web.multipart.MultipartFile;
//
//public class IdeaBuilder {
//    private Long id;
//
//    private String brief;
//
//    private String content;
//
//    private String author;
//
//    private MultipartFile file;
//
//    private long topicId;
//
//    private long categoryId;
//
//    public IdeaBuilder(String name) {
//        this.name = name;
//    }
//
//    public IdeaBuilder description(String description) {
//        this.description = description;
//        return this;
//    }
//
//    public IdeaBuilder image(String image) {
//        this.image = image;
//        return this;
//    }
//
//    public IdeaRequest build() {
//        return new IdeaRequest(this);
//    }
//}