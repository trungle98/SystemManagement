package com.edu.greenwich.managementsystem.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReactionWithIdeaIdResponse {
    private int id;

    private String author;
    private String brief;

    private String content;

    private String file;

    private long categoryId;

    private long topicId;

    private int views;
    int totalLike;
    int totalDislike;

    public ReactionWithIdeaIdResponse(int id, String author, String brief, String content, String file, long categoryId, long topicId, int views, int totalLike, int totalDislike) {
        this.id = id;
        this.author = author;
        this.brief = brief;
        this.content = content;
        this.file = file;
        this.categoryId = categoryId;
        this.topicId = topicId;
        this.views = views;
        this.totalLike = totalLike;
        this.totalDislike = totalDislike;
    }


}
