package com.edu.greenwich.managementsystem.Repository.impl;

import com.edu.greenwich.managementsystem.Repository.AnalyticsRepository;
import com.edu.greenwich.managementsystem.model.Analytics;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnalyticsRepositoryImpl implements AnalyticsRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Analytics> analystByDepartment() {
        javax.persistence.Query query = entityManager.createNativeQuery("SELECT d.department_id, d.name, COUNT(idea.idea_id) AS num_ideas FROM department d  LEFT JOIN users ON d.department_id = users.department_id  LEFT JOIN idea ON users.user_id = idea.author GROUP BY d.department_id, d.name");
        List<Object[]> rows = query.getResultList();
        List<Analytics> result = new ArrayList<>(rows.size());
        for(Object[] row: rows){
            result.add(Analytics.builder().id((int)row[0])
                    .name(row[1].toString())
                    .number(Integer.valueOf(row[2].toString()))
                    .build());
        }
        return result;
    }

    @Override
    public List<Analytics> getAllCommentByBullying() {
        javax.persistence.Query query = entityManager.createNativeQuery("select sum(case c.bullying when 'not_cyberbullying' then 1 else 0 end) as not_cyberbullying, sum(case c.bullying when 'is_cyberbullying' then 1 else 0 end) as is_cyberbullying\n" +
                "from comment c");
        List<Object[]> rows = query.getResultList();
        List<Analytics> result = new ArrayList<>(rows.size());
        int count = 0;
        for(Object[] row: rows){
            result.add(Analytics.builder().id(count)
                    .name(row[0].toString())
                    .number(Integer.valueOf(row[1].toString()))
                    .build());
        }
        return result;
    }

    @Override
    public List<Analytics> getTop5UserBullying() {
        javax.persistence.Query query = entityManager.createNativeQuery("select u.username, count(c.bullying = 'is_cyberbullying') from comment c join users u on c.created_by = u.user_id group by c.created_by order by count(c.bullying = 'is_cyberbullying') DESC limit 5");
        List<Object[]> rows = query.getResultList();
        List<Analytics> result = new ArrayList<>(rows.size());
        int count = 0;
        for(Object[] row: rows){
            result.add(Analytics.builder().id(count)
                    .name(row[0].toString())
                    .number(Integer.valueOf(row[1].toString()))
                    .build());
            count+=1;
        }
        return result;
    }
}
