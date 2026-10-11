package com.feedback.platform.dto;

public class CategoryRatingDto {

    private String category;
    private double averageRating;
    private long count;

    public CategoryRatingDto() {
    }

    public CategoryRatingDto(String category, double averageRating, long count) {
        this.category = category;
        this.averageRating = averageRating;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
