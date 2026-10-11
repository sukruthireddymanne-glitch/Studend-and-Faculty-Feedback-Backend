package com.feedback.platform.dto;

import java.util.List;

public class AnalyticsOverviewDto {

    private long totalSubmissions;
    private double overallAverageRating;
    private long totalStudents;
    private long totalFaculty;
    private long studentToFacultyCount;
    private long facultyToStudentCount;
    private List<RatingDistributionDto> ratingDistributions;
    private List<CategoryRatingDto> categoryAverages;

    public AnalyticsOverviewDto() {
    }

    public long getTotalSubmissions() {
        return totalSubmissions;
    }

    public void setTotalSubmissions(long totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }

    public double getOverallAverageRating() {
        return overallAverageRating;
    }

    public void setOverallAverageRating(double overallAverageRating) {
        this.overallAverageRating = overallAverageRating;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalFaculty() {
        return totalFaculty;
    }

    public void setTotalFaculty(long totalFaculty) {
        this.totalFaculty = totalFaculty;
    }

    public long getStudentToFacultyCount() {
        return studentToFacultyCount;
    }

    public void setStudentToFacultyCount(long studentToFacultyCount) {
        this.studentToFacultyCount = studentToFacultyCount;
    }

    public long getFacultyToStudentCount() {
        return facultyToStudentCount;
    }

    public void setFacultyToStudentCount(long facultyToStudentCount) {
        this.facultyToStudentCount = facultyToStudentCount;
    }

    public List<RatingDistributionDto> getRatingDistributions() {
        return ratingDistributions;
    }

    public void setRatingDistributions(List<RatingDistributionDto> ratingDistributions) {
        this.ratingDistributions = ratingDistributions;
    }

    public List<CategoryRatingDto> getCategoryAverages() {
        return categoryAverages;
    }

    public void setCategoryAverages(List<CategoryRatingDto> categoryAverages) {
        this.categoryAverages = categoryAverages;
    }
}
