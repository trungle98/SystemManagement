package com.edu.greenwich.managementsystem.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {
    public void init();

    public String save(MultipartFile file, int topicId);

    Resource load(String filename);
    public Resource ExportToZip(String topicId)  throws IOException;
    void removeTempZipAfterDownload(Path tempFolderPath);
}