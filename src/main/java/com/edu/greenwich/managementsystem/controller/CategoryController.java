package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.CategoryRepository;
import com.edu.greenwich.managementsystem.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/category")

public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping("/all")
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    @PreAuthorize("hasRole('ADMIN')  or hasRole('manager')")
    @GetMapping("/get/")
    public Optional<Category> findById(@RequestParam int id) {
        return categoryRepository.findById(id);
    }
    @PreAuthorize("hasRole('ADMIN')  or hasRole('manager')")
    @PostMapping("/save")
    public Category save(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
    @PreAuthorize("hasRole('ADMIN')  or hasRole('manager')")
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        categoryRepository.deleteById(id);
    }
}
