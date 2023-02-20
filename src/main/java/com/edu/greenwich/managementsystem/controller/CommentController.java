package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.CommentRepository;
import com.edu.greenwich.managementsystem.Repository.IdeaRepository;
import com.edu.greenwich.managementsystem.model.Comment;
import com.edu.greenwich.managementsystem.model.Idea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/all")
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @PostMapping("/save")
    public Comment save(@RequestBody Comment comment) {
        Date now = new Date();
        comment.setCreatedDate(now);
        return commentRepository.save(comment);
    }

    public Optional<Object> delete(int id) {
        return Optional.empty();
    }
}
