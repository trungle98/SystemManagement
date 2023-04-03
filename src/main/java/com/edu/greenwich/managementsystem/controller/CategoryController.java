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
@PreAuthorize("hasRole('ADMIN')  or hasRole('manager')")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/all")
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/get/")
    public Optional<Category> findById(@RequestParam int id) {
        return categoryRepository.findById(id);
    }

    @PostMapping("/save")
    public Category save(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        categoryRepository.deleteById(id);
    }
}
