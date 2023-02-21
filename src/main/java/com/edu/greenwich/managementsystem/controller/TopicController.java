package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.TopicRepository;
import com.edu.greenwich.managementsystem.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/all")
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @GetMapping("/get")
    public Optional<Topic> findById(@RequestParam long id) {
        return topicRepository.findById(id);
    }

    @PostMapping("/save")
    public Topic save(@RequestBody Topic topic) {
        return topicRepository.save(topic);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam long id) {
        topicRepository.deleteById(id);
    }
}
