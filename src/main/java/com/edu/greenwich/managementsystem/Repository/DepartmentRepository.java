package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.model.Comment;
import com.edu.greenwich.managementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}