package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.UserRepository;
import com.edu.greenwich.managementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable long id) {
        return userRepository.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @PutMapping ("/{id}")
    public User update(@RequestBody User user, @PathVariable long id) {
        Optional<User> u = userRepository.findByUserId(id);
        if (u.isPresent()){
            u.get().setEmail(user.getEmail());
            u.get().setPassword(user.getPassword());
            u.get().setDepartments(user.getDepartments());
        }
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @DeleteMapping("/")
    public void delete(@PathVariable long id) {
        userRepository.deleteById(id);
    }
}
