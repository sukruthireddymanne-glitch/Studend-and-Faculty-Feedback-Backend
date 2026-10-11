package com.feedback.platform.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewee_id", nullable = false)
    private User reviewee;

    @Enumerated(EnumType.STRING)
    @Column(name = "feedback_type", nullable = false)
    private FeedbackType feedbackType;

    @Column(nullable = false)
    private Integer rating; // Overall rating (1-5)

    @Column(name = "teaching_quality_rating")
    private Integer teachingQualityRating; // 1-5, applies to faculty review

    @Column(name = "communication_rating")
    private Integer communicationRating; // 1-5, applies to both

    @Column(name = "subject_knowledge_rating")
    private Integer subjectKnowledgeRating; // 1-5, applies to faculty (or student subject understanding)

    @Column(name = "participation_rating")
    private Integer participationRating; // 1-5, applies to student review

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Feedback() {
    }

    public Feedback(User reviewer, User reviewee, FeedbackType feedbackType, Integer rating,
                    Integer teachingQualityRating, Integer communicationRating,
                    Integer subjectKnowledgeRating, Integer participationRating, String comments) {
        this.reviewer = reviewer;
        this.reviewee = reviewee;
        this.feedbackType = feedbackType;
        this.rating = rating;
        this.teachingQualityRating = teachingQualityRating;
        this.communicationRating = communicationRating;
        this.subjectKnowledgeRating = subjectKnowledgeRating;
        this.participationRating = participationRating;
        this.comments = comments;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public User getReviewee() {
        return reviewee;
    }

    public void setReviewee(User reviewee) {
        this.reviewee = reviewee;
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
