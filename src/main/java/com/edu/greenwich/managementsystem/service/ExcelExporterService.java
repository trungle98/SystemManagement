package com.edu.greenwich.managementsystem.service;

import com.edu.greenwich.managementsystem.dto.response.ReactionWithIdeaIdResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface ExcelExporterService {
    public ByteArrayInputStream export(List<ReactionWithIdeaIdResponse> objects) throws IOException;
}
