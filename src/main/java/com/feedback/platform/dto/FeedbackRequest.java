package com.feedback.platform.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeedbackRequest {

    @NotNull(message = "Reviewee ID is required")
    private Long revieweeId;

    @NotNull(message = "Overall rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating;

    @Min(value = 1, message = "Teaching quality rating must be between 1 and 5")
    @Max(value = 5, message = "Teaching quality rating must be between 1 and 5")
    private Integer teachingQualityRating;

    @Min(value = 1, message = "Communication rating must be between 1 and 5")
    @Max(value = 5, message = "Communication rating must be between 1 and 5")
    private Integer communicationRating;

    @Min(value = 1, message = "Subject knowledge rating must be between 1 and 5")
    @Max(value = 5, message = "Subject knowledge rating must be between 1 and 5")
    private Integer subjectKnowledgeRating;

    @Min(value = 1, message = "Participation rating must be between 1 and 5")
    @Max(value = 5, message = "Participation rating must be between 1 and 5")
    private Integer participationRating;

    @Size(max = 1000, message = "Comments cannot exceed 1000 characters")
    private String comments;

    public FeedbackRequest() {
    }

    public Long getRevieweeId() {
        return revieweeId;
    }

    public void setRevieweeId(Long revieweeId) {
        this.revieweeId = revieweeId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getTeachingQualityRating() {
        return teachingQualityRating;
    }

    public void setTeachingQualityRating(Integer teachingQualityRating) {
        this.teachingQualityRating = teachingQualityRating;
    }

    public Integer getCommunicationRating() {
        return communicationRating;
    }

    public void setCommunicationRating(Integer communicationRating) {
        this.communicationRating = communicationRating;
    }

    public Integer getSubjectKnowledgeRating() {
        return subjectKnowledgeRating;
    }

    public void setSubjectKnowledgeRating(Integer subjectKnowledgeRating) {
        this.subjectKnowledgeRating = subjectKnowledgeRating;
    }

    public Integer getParticipationRating() {
        return participationRating;
    }

    public void setParticipationRating(Integer participationRating) {
        this.participationRating = participationRating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
