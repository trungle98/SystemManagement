package com.edu.greenwich.managementsystem.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
public class Analytics {
    int id;
    String name;
    int number;
}
