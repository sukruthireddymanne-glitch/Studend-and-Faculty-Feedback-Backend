package com.feedback.platform.repository;

import com.feedback.platform.entity.Feedback;
import com.feedback.platform.entity.FeedbackType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByReviewerIdOrderByCreatedAtDesc(Long reviewerId);

    List<Feedback> findByRevieweeIdOrderByCreatedAtDesc(Long revieweeId);

    boolean existsByReviewerIdAndRevieweeIdAndFeedbackType(Long reviewerId, Long revieweeId, FeedbackType feedbackType);

    long countByFeedbackType(FeedbackType feedbackType);

    long countByRating(Integer rating);

    @Query("SELECT AVG(f.rating) FROM Feedback f")
    Double getOverallAverageRating();

    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.reviewee.id = :revieweeId")
    Double getAverageRatingForReviewee(@Param("revieweeId") Long revieweeId);

    @Query("SELECT AVG(f.teachingQualityRating) FROM Feedback f WHERE f.teachingQualityRating IS NOT NULL")
    Double getAverageTeachingQuality();

    @Query("SELECT AVG(f.communicationRating) FROM Feedback f WHERE f.communicationRating IS NOT NULL")
    Double getAverageCommunication();

    @Query("SELECT AVG(f.subjectKnowledgeRating) FROM Feedback f WHERE f.subjectKnowledgeRating IS NOT NULL")
    Double getAverageSubjectKnowledge();

    @Query("SELECT AVG(f.participationRating) FROM Feedback f WHERE f.participationRating IS NOT NULL")
    Double getAverageParticipation();

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.teachingQualityRating IS NOT NULL")
    Long countTeachingQualitySubmissions();

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.communicationRating IS NOT NULL")
    Long countCommunicationSubmissions();

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.subjectKnowledgeRating IS NOT NULL")
    Long countSubjectKnowledgeSubmissions();

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.participationRating IS NOT NULL")
    Long countParticipationSubmissions();
}
