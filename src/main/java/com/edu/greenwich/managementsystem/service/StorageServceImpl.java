package com.edu.greenwich.managementsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    public String save(MultipartFile file, int topicId) {
        createSubmissionDirIfNotExist(String.valueOf(topicId));
        Path currPath = root.resolve(Paths.get(String.valueOf(topicId)));
        String completedPath = "";
        String fileName = file.getOriginalFilename().split("\\.")[0].concat(String.valueOf(System.currentTimeMillis()).concat(".").concat(file.getOriginalFilename().split("\\.")[1]));
        try {
            Files.copy(file.getInputStream(), currPath.resolve(fileName));
            completedPath = currPath.resolve(fileName).toString();
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
    private void createSubmissionDirIfNotExist(String dirName) {
        Iterator<Path> iterator = root.iterator();
        StringBuilder rootName = new StringBuilder();
        while (iterator.hasNext()) {
            Path component = iterator.next();
            rootName.append(component);
        }
        File folder = new File(rootName.toString().concat("/").concat(dirName));
        if (!folder.exists()) {
            folder.mkdirs(); // creates the folder and any necessary parent folders
        }
    }

    public Resource ExportToZip(String topicId) throws IOException {
        String folderName = topicId;
        Path tempFolderPath = Files.createTempDirectory("temp-zip");
        Path tempZipFilePath = Files.createTempFile("temp-zip", ".zip");

        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(tempZipFilePath.toFile()))) {
            Files.walk(root)
                    .filter(path -> path.toFile().isDirectory() && path.getFileName().toString().equals(folderName))
                    .forEach(path -> {
                        try {
                            zipOut.putNextEntry(new ZipEntry(root.relativize(path).toString() + "/"));
                            zipOut.closeEntry();
                            Files.walk(path).filter(p -> !p.toFile().isDirectory()).forEach(childPath -> {
                                try {
                                    zipOut.putNextEntry(new ZipEntry(root.relativize(childPath).toString()));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    Files.copy(childPath, zipOut);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    zipOut.closeEntry();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (IOException e) {
                            System.err.println("Failed to zip folder: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("Failed to create zip file: " + e.getMessage());
        }
        Resource resource = new UrlResource(tempZipFilePath.toUri());
        return resource;
    }
    public void removeTempZipAfterDownload(Path tempFolderPath) {
        try {
            Files.walk(tempFolderPath).sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    System.err.println("Failed to delete temporary file or directory: " + e.getMessage());
                }
            });
        } catch (IOException e) {
            System.err.println("Failed to delete temporary file or directory: " + e.getMessage());
        }
    }
}
