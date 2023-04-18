package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.model.Analytics;

import java.util.List;

public interface AnalyticsRepository {
    public List<Analytics> analystByDepartment();
    List<Analytics> getAllCommentByBullying();

    List<Analytics> getTop5UserBullying();
}
