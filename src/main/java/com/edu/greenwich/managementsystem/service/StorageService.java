package com.edu.greenwich.managementsystem.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    public void init();

    public String save(MultipartFile file);

    Resource load(String filename);
}