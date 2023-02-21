package com.edu.greenwich.managementsystem.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdeaResponse {
    private Long id;

    private String brief;

    private String content;

    private String author;

    private String file;

    private long topicId;

    private long categoryId;
}
