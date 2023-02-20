package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
}