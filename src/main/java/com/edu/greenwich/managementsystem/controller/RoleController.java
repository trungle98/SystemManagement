package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.RoleRepository;
import com.edu.greenwich.managementsystem.dto.request.RoleDto;
import com.edu.greenwich.managementsystem.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/role")
@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public List<RoleDto> getAll() {
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<RoleDto> findById(@PathVariable int id) {
        return roleRepository.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @PostMapping("/save")
    public RoleDto save(@RequestBody RoleDto role) {
        return roleRepository.save(role);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @PostMapping ("/{id}")
    public RoleDto update(@RequestBody RoleDto role, @PathVariable int id) {

        return roleRepository.save(role);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        roleRepository.deleteById(id);
    }
}
