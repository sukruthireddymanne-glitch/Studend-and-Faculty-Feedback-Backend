package com.feedback.platform.dto;

import com.feedback.platform.entity.FeedbackType;
import com.feedback.platform.entity.Role;
import java.time.LocalDateTime;

public class FeedbackResponse {

    private Long id;
    private Long reviewerId;
    private String reviewerName;
    private Role reviewerRole;
    private Long revieweeId;
    private String revieweeName;
    private Role revieweeRole;
    private FeedbackType feedbackType;
    private Integer rating;
    private Integer teachingQualityRating;
    private Integer communicationRating;
    private Integer subjectKnowledgeRating;
    private Integer participationRating;
    private String comments;
    private LocalDateTime createdAt;

    public FeedbackResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Role getReviewerRole() {
        return reviewerRole;
    }

    public void setReviewerRole(Role reviewerRole) {
        this.reviewerRole = reviewerRole;
    }

    public Long getRevieweeId() {
        return revieweeId;
    }

    public void setRevieweeId(Long revieweeId) {
        this.revieweeId = revieweeId;
    }

    public String getRevieweeName() {
        return revieweeName;
    }

    public void setRevieweeName(String revieweeName) {
        this.revieweeName = revieweeName;
    }

    public Role getRevieweeRole() {
        return revieweeRole;
    }

    public void setRevieweeRole(Role revieweeRole) {
        this.revieweeRole = revieweeRole;
    }

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
