package com.feedback.platform.controller;

import com.feedback.platform.dto.AnalyticsOverviewDto;
import com.feedback.platform.dto.ApiResponse;
import com.feedback.platform.dto.CategoryRatingDto;
import com.feedback.platform.dto.RatingDistributionDto;
import com.feedback.platform.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/overview")
    public ResponseEntity<ApiResponse<AnalyticsOverviewDto>> getOverview() {
        AnalyticsOverviewDto dto = analyticsService.getOverview();
        return ResponseEntity.ok(ApiResponse.ok("Analytics overview retrieved successfully", dto));
    }

    @GetMapping("/ratings")
    public ResponseEntity<ApiResponse<List<RatingDistributionDto>>> getRatingsDistribution() {
        List<RatingDistributionDto> list = analyticsService.getRatingsDistribution();
        return ResponseEntity.ok(ApiResponse.ok("Rating distributions retrieved successfully", list));
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CategoryRatingDto>>> getCategoryAverages() {
        List<CategoryRatingDto> list = analyticsService.getCategoryAverages();
        return ResponseEntity.ok(ApiResponse.ok("Category averages retrieved successfully", list));
    }
}
