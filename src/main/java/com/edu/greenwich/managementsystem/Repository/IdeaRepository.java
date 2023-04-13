package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.dto.response.ReactionWithIdeaIdResponse;
import com.edu.greenwich.managementsystem.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Integer> {
    @Query(value = "select i.*, sum(case r.is_like when true then 1 else 0 end) as total_like, sum(case r.is_like when false then 1 else 0 end) as total_dislike from reaction r join idea i on r.idea_id = i.idea_id where r.idea_id in (select idea_id from idea where topic_id = ?1) group by r.idea_id", nativeQuery = true)
    Optional<List<ReactionWithIdeaIdResponse>> findByTopicId(int topicId);
}