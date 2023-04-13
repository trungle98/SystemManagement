package com.edu.greenwich.managementsystem.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Data
public class IdeaRequest {
    @Nullable
    private int id;

    private String brief;

    private String content;

    private String author;


    @Nullable
    private MultipartFile file;
    @Nullable
    private int topicId;

    @Nullable
    private long categoryId;

}
