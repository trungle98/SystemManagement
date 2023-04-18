package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Idea, Integer> {
    @Query(value = "select * from idea i order by i.views desc limit 5", nativeQuery = true)
    public List<Idea> getTopViewsIdea();

    @Query(value = "select idea.*, sum(case r.is_like when true then 1 else 0 end) as totalLike from idea join reaction r on idea.idea_id = r.idea_id group by idea.idea_id order by sum(case r.is_like when true then 1 else 0 end) desc limit 5", nativeQuery = true)
    public List<Idea> getTopLikeIdea();

}
