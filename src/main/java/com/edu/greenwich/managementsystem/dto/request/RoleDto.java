package com.edu.greenwich.managementsystem.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    int id;
    String name;

}
