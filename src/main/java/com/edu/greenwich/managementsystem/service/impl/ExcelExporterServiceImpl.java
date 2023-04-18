package com.edu.greenwich.managementsystem.service.impl;

import com.edu.greenwich.managementsystem.dto.response.ReactionWithIdeaIdResponse;
import com.edu.greenwich.managementsystem.service.ExcelExporterService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExporterServiceImpl implements ExcelExporterService {
    @Override
    public ByteArrayInputStream export(List<ReactionWithIdeaIdResponse> objects) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("My Objects");

        // Create header row
        Row header = ((Sheet) sheet).createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("brief");
        header.createCell(2).setCellValue("content");
        header.createCell(3).setCellValue("author");
        header.createCell(4).setCellValue("file_location");
        header.createCell(8).setCellValue("topicid");
        header.createCell(5).setCellValue("views");
        header.createCell(6).setCellValue("like");
        header.createCell(7).setCellValue("dislike");
        // Create data rows
        int rowNum = 1;
        for (ReactionWithIdeaIdResponse object : objects) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(object.getId());
            row.createCell(1).setCellValue(object.getBrief());
            row.createCell(2).setCellValue(object.getContent());
            row.createCell(3).setCellValue(object.getAuthor());
            row.createCell(4).setCellValue(object.getFile());
            row.createCell(5).setCellValue(object.getTopicId());
            row.createCell(6).setCellValue(object.getViews());
            row.createCell(7).setCellValue(object.getTotalLike());
            row.createCell(8).setCellValue(object.getTotalDislike());
        }

        // Write workbook to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        // Clean up
        workbook.close();
        outputStream.close();

        return inputStream;
    }
}
