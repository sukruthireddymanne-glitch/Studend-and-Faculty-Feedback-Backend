package com.feedback.platform.dto;

public class RatingDistributionDto {

    private int star;
    private long count;
    private double percentage;

    public RatingDistributionDto() {
    }

    public RatingDistributionDto(int star, long count, double percentage) {
        this.star = star;
        this.count = count;
        this.percentage = percentage;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
