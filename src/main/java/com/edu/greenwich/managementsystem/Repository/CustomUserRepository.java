package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.dto.response.UserDto;

import java.util.List;

public interface CustomUserRepository {
    List<UserDto> getAll();

}
