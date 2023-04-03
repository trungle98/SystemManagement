package com.edu.greenwich.managementsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageServceImpl implements StorageService{
    private static Logger logger = LoggerFactory.getLogger(StorageServceImpl.class);
    private final Path root = Paths.get("uploads");
    @Override
    public void init() {
        logger.info("init storage");
        try {
            Files.createDirectories(root);
            logger.info("init storage successfully");
        } catch (IOException e) {
            logger.error("Could not create folder for upload file, error ={}", e);
            throw new RuntimeException("Could not create folder for upload file");
        }
    }

    @Override
    public String save(MultipartFile file) {
        String completedPath = "";
        String fileName = file.getOriginalFilename().split("\\.")[0].concat(String.valueOf(System.currentTimeMillis()).concat(".").concat(file.getOriginalFilename().split("\\.")[1]));
        try {
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
            completedPath = root.resolve(fileName).toString();
            logger.info("saved ={} successfully", fileName);
            return completedPath;
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                logger.error("A file of that name already exists or do not have write permission.");
                throw new RuntimeException("A file of that name already exists or do not have write permission.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            filename = filename.replaceFirst("uploads/", "");
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
