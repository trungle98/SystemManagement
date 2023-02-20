package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    @Modifying
    @Query(value = "update reaction r set r.is_like = :isLike where r.idea_id = :ideaId and r.user_id = :userId", nativeQuery = true)
    void saveReaction(@Param(value = "isLike") boolean isLike, @Param(value = "ideaId")  String ideaId, @Param(value = "userId")  String userId);
}