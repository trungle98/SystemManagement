package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.TopicRepository;
import com.edu.greenwich.managementsystem.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;

    @PreAuthorize("hasRole('ROLE_STAFF') or hasRole('dept') or hasRole('admin') or hasRole('manager')")
    @GetMapping("/all")
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_STAFF') or hasRole('dept') or hasRole('admin') or hasRole('manager')")
    @GetMapping("/get")
    public Optional<Topic> findById(@RequestParam int id) {
        return topicRepository.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')  or hasRole('manager')")
    @PostMapping("/save")
    public Topic save(@RequestBody Topic topic) {
        return topicRepository.save(topic);
    }

    @PreAuthorize("hasRole('ADMIN')  or hasRole('manager')")
    @GetMapping("/delete")
    public void delete(@RequestParam int id) {
        topicRepository.deleteById(id);
    }
}
