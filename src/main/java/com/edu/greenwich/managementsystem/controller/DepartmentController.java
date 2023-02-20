package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.DepartmentRepository;
import com.edu.greenwich.managementsystem.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/dept")
public class DepartmentController{

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/all")
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Optional<Object> findById(int id) {
        return Optional.empty();
    }

    @PostMapping("/save")
    public Department save(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Object> delete(int id) {
        return Optional.empty();
    }
}
