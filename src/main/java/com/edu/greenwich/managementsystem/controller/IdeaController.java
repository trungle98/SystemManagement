package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.CommentRepository;
import com.edu.greenwich.managementsystem.Repository.IdeaRepository;
import com.edu.greenwich.managementsystem.model.Comment;
import com.edu.greenwich.managementsystem.model.Idea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/idea")
public class IdeaController {
    @Autowired
    IdeaRepository ideaRepository;
    @Autowired
    CommentRepository commentRepository;
    @GetMapping("/all")
    public List<Idea> getAll() {
        return ideaRepository.findAll();
    }

    @GetMapping("/")
    public Optional<Idea> findById(@RequestParam long id) {
        return ideaRepository.findById(id);
    }

    @PostMapping("/save")
    public Idea save(@RequestBody Idea idea) {
        return ideaRepository.save(idea);
    }

    public void delete(@RequestParam long id) {
        ideaRepository.deleteById(id);
    }

    @GetMapping("/getAllComment")
    public List<Comment> getAllComment(@RequestParam long ideaId){
        return commentRepository.findAllByIdeaId(ideaId);
    }

}
