package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.AnalyticsRepository;
import com.edu.greenwich.managementsystem.model.Analytics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/analytics")
@PreAuthorize("hasRole('ROLE_DEPT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
public class AnalyticsController {
    @Autowired
    AnalyticsRepository analyticsRepository;
    @GetMapping("/byDepartment")
    public List<Analytics> getAll() {
        return analyticsRepository.analystByDepartment();
    }

    @GetMapping("/countCyberBullying")
    public List<Analytics> getAllByBullying() {
        return analyticsRepository.getAllCommentByBullying();
    }

    @GetMapping("/topBullyingUser")
    public List<Analytics> getTopBullyingUser() {
        return analyticsRepository.getTop5UserBullying();
    }
}
