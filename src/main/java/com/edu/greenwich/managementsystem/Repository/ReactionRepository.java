package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.dto.response.ReactionWithIdeaIdResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReactionRepository{

    void saveReaction(@Param(value = "isLike") boolean isLike, @Param(value = "ideaId")  String ideaId, @Param(value = "userId")  String userId);

    List<ReactionWithIdeaIdResponse> getReactionByTopicId(int topicId);
}