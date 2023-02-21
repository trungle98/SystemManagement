package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.model.Category;
import com.edu.greenwich.managementsystem.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}