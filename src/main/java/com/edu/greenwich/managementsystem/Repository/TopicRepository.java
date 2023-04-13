package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends PagingAndSortingRepository<Topic, Integer> {
    @Query(value = "select * from topic where topic_id = ?1", nativeQuery = true)
    Topic getById(int topicId);

    @Query(value = "select * from topic where topic_id = (select topic_id from idea where idea_id=?1)", nativeQuery = true)
    Topic getByIdeaId(long ideaId);
}