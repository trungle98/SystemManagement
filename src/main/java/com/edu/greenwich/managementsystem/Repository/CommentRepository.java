package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "select * from comment c where c.idea_id = ?1", nativeQuery = true)
    List<Comment> findAllByIdeaId(long ideaId);
}