package com.feedback.platform.service;

import com.feedback.platform.dto.AnalyticsOverviewDto;
import com.feedback.platform.dto.CategoryRatingDto;
import com.feedback.platform.dto.RatingDistributionDto;
import com.feedback.platform.entity.FeedbackType;
import com.feedback.platform.entity.Role;
import com.feedback.platform.repository.FeedbackRepository;
import com.feedback.platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    public AnalyticsService(FeedbackRepository feedbackRepository, UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
    }

    public AnalyticsOverviewDto getOverview() {
        AnalyticsOverviewDto dto = new AnalyticsOverviewDto();
        long total = feedbackRepository.count();
        dto.setTotalSubmissions(total);

        Double avg = feedbackRepository.getOverallAverageRating();
        dto.setOverallAverageRating(round(avg != null ? avg : 0.0));

        dto.setTotalStudents(userRepository.countByRole(Role.STUDENT));
        dto.setTotalFaculty(userRepository.countByRole(Role.FACULTY));
        dto.setStudentToFacultyCount(feedbackRepository.countByFeedbackType(FeedbackType.STUDENT_TO_FACULTY));
        dto.setFacultyToStudentCount(feedbackRepository.countByFeedbackType(FeedbackType.FACULTY_TO_STUDENT));

        dto.setRatingDistributions(getRatingDistributions(total));
        dto.setCategoryAverages(getCategoryAverages());

        return dto;
    }

    public List<RatingDistributionDto> getRatingsDistribution() {
        long total = feedbackRepository.count();
        return getRatingDistributions(total);
    }

    public List<CategoryRatingDto> getCategoryAverages() {
        List<CategoryRatingDto> list = new ArrayList<>();

        Double tqAvg = feedbackRepository.getAverageTeachingQuality();
        Long tqCount = feedbackRepository.countTeachingQualitySubmissions();
        list.add(new CategoryRatingDto("Teaching Quality", round(tqAvg != null ? tqAvg : 0.0), tqCount != null ? tqCount : 0));

        Double commAvg = feedbackRepository.getAverageCommunication();
        Long commCount = feedbackRepository.countCommunicationSubmissions();
        list.add(new CategoryRatingDto("Communication", round(commAvg != null ? commAvg : 0.0), commCount != null ? commCount : 0));

        Double skAvg = feedbackRepository.getAverageSubjectKnowledge();
        Long skCount = feedbackRepository.countSubjectKnowledgeSubmissions();
        list.add(new CategoryRatingDto("Subject Knowledge", round(skAvg != null ? skAvg : 0.0), skCount != null ? skCount : 0));

        Double partAvg = feedbackRepository.getAverageParticipation();
        Long partCount = feedbackRepository.countParticipationSubmissions();
        list.add(new CategoryRatingDto("Participation & Performance", round(partAvg != null ? partAvg : 0.0), partCount != null ? partCount : 0));

        return list;
    }

    private List<RatingDistributionDto> getRatingDistributions(long total) {
        List<RatingDistributionDto> list = new ArrayList<>();
        for (int star = 5; star >= 1; star--) {
            long count = feedbackRepository.countByRating(star);
            double pct = total > 0 ? ((double) count / total) * 100.0 : 0.0;
            list.add(new RatingDistributionDto(star, count, round(pct)));
        }
        return list;
    }

    private double round(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
