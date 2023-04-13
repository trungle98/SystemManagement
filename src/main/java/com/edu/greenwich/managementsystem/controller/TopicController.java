package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.TopicRepository;
import com.edu.greenwich.managementsystem.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;

    @PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_DEPT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public Page<Topic> getAll(
            @RequestParam(defaultValue = "0") Integer pageNo) {
        int pageSize = 5;
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Topic> pagedResult = topicRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }

    @PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_DEPT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @GetMapping("/get")
    public Optional<Topic> findById(@RequestParam int id) {
        return topicRepository.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_STAFF') ")
    @PostMapping("/save")
    public Topic save(@RequestBody Topic topic) {
        return topicRepository.save(topic);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_DEPT') ")
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        topicRepository.deleteById(id);
    }
}
