package com.edu.greenwich.managementsystem.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class UserDto {
    private BigInteger userId;
    private String email;
    private String password;
    private String username;
    private String deptName;
    private String role;
}
