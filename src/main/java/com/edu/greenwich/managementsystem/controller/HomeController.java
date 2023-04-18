package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.HomeRepository;
import com.edu.greenwich.managementsystem.model.Analytics;
import com.edu.greenwich.managementsystem.model.Idea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    HomeRepository homeRepository;

    @GetMapping("/getTopViewsIdea")
    public List<Idea> getTopViewsIdea() {
        return homeRepository.getTopViewsIdea();
    }

    @GetMapping("/getTopLikeIdea")
    public List<Idea> getTopLikeIdea() {
        return homeRepository.getTopLikeIdea();
    }
}
