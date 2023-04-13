package com.edu.greenwich.managementsystem.Repository.impl;

import com.edu.greenwich.managementsystem.Repository.ReactionRepository;
import com.edu.greenwich.managementsystem.dto.response.ReactionWithIdeaIdResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomIdeaRepoImpl implements ReactionRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional
    public void saveReaction(boolean isLike, String ideaId, String userId) {
        Query query = entityManager.createNativeQuery("update reaction set is_like=? where idea_id=? and user_id=?");
        query.setParameter(1, isLike);
        query.setParameter(2, ideaId);
        query.setParameter(3, userId);
        int isUpdate = query.executeUpdate();
        if(isUpdate == 0){
        Query insert = entityManager.createNativeQuery("insert into reaction (is_like, idea_id, user_id) values (?,?,?)");

            insert.setParameter(1, isLike);
            insert.setParameter(2, ideaId);
            insert.setParameter(3, userId);
            insert.executeUpdate();
        }
    }

    @Override
    public List<ReactionWithIdeaIdResponse> getReactionByTopicId(int topicId) {
        Query query = entityManager.createNativeQuery("select i.*, sum(case r.is_like when true then 1 else 0 end) as total_like, sum(case r.is_like when false then 1 else 0 end) as total_dislike from reaction r right join idea i on r.idea_id = i.idea_id where i.idea_id in (select idea_id from idea where topic_id = ?) group by i.idea_id");
        query.setParameter(1, topicId);
        List<Object[]> rows = query.getResultList();
        List<ReactionWithIdeaIdResponse> result = new ArrayList<>(rows.size());
        for(Object[] row: rows){
            result.add(ReactionWithIdeaIdResponse.builder().id((int)row[0])
                    .author(row[1].toString())
                    .brief(row[2] == null?String.valueOf("null"):row[2].toString())
                    .content(row[3] == null?String.valueOf("null"):row[3].toString())
                    .file(row[4] == null?String.valueOf("null"):row[4].toString())
                    .categoryId(Long.valueOf(row[5].toString()))
                    .topicId(Long.valueOf(row[6].toString()))
                    .views(Integer.valueOf(row[7].toString()))
                    .totalLike(Integer.valueOf(row[8].toString()))
                    .totalDislike(Integer.valueOf(row[9].toString()))
                    .build());
        }
        return result;
    }
}
