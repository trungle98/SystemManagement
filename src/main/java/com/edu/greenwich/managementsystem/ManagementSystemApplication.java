package com.edu.greenwich.managementsystem;

import com.edu.greenwich.managementsystem.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import javax.annotation.Resource;

@SpringBootApplication
public class ManagementSystemApplication {
    private static Logger logger = LoggerFactory.getLogger(ManagementSystemApplication.class);
    @Resource
    StorageService storageService;
    public static void main(String[] args) {
        SpringApplication.run(ManagementSystemApplication.class, args);
    }
    public void run(String... arg) throws Exception {
        logger.info("init folder for file uploading!");
        storageService.init();
    }
}
