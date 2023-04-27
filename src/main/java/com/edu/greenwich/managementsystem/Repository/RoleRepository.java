package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.dto.request.RoleDto;
import com.edu.greenwich.managementsystem.model.ERole;
import com.edu.greenwich.managementsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(ERole name);

    List<RoleDto> findAll();

    Optional<RoleDto> findById(int id);

    RoleDto save(RoleDto role);

    void deleteById(int id);
}