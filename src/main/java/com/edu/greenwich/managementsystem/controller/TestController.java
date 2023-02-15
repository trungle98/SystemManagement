package com.edu.greenwich.managementsystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('staff') or hasRole('dept') or hasRole('admin') or hasRole('manager')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/dept")
    @PreAuthorize("hasRole('dept')")
    public String moderatorAccess() {
        return "dept Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasRole('manager')")
    public String managerAccess() {
        return "manager Board.";
    }
}