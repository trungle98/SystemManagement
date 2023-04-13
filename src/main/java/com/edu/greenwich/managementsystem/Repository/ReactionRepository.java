package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.dto.response.ReactionWithIdeaIdResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReactionRepository{

    void saveReaction(boolean isLike, String ideaId, String userId);

    List<ReactionWithIdeaIdResponse> getReactionByTopicId(int topicId);
}