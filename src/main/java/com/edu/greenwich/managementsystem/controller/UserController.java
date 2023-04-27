package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.CustomUserRepository;
import com.edu.greenwich.managementsystem.Repository.RoleRepository;
import com.edu.greenwich.managementsystem.Repository.UserRepository;
import com.edu.greenwich.managementsystem.dto.response.UserDto;
import com.edu.greenwich.managementsystem.model.Department;
import com.edu.greenwich.managementsystem.model.ERole;
import com.edu.greenwich.managementsystem.model.Role;
import com.edu.greenwich.managementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomUserRepository customUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public List<UserDto> getAll() {
        return customUserRepository.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable long id) {
        return userRepository.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @PostMapping("/save")
    public User save(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        Role userRole = new Role();
        userRole.setId(1);
        userRole.setName(ERole.ROLE_STAFF);
        roles.add(userRole);
        user.setDepartments(new Department(1));
        user.setRoles(roles);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @PostMapping ("/{id}")
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
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        userRepository.DeleteByUserId(id);
    }
}
