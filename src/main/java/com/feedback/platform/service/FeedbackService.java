package com.feedback.platform.service;

import com.feedback.platform.dto.FeedbackRequest;
import com.feedback.platform.dto.FeedbackResponse;
import com.feedback.platform.entity.Feedback;
import com.feedback.platform.entity.FeedbackType;
import com.feedback.platform.entity.Role;
import com.feedback.platform.entity.User;
import com.feedback.platform.exception.BadRequestException;
import com.feedback.platform.repository.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserService userService;

    public FeedbackService(FeedbackRepository feedbackRepository, UserService userService) {
        this.feedbackRepository = feedbackRepository;
        this.userService = userService;
    }

    @Transactional
    public FeedbackResponse submitStudentToFaculty(String studentEmail, FeedbackRequest request) {
        User student = userService.getUserByEmail(studentEmail);
        if (student.getRole() != Role.STUDENT) {
            throw new BadRequestException("Only students can submit student-to-faculty feedback");
        }

        User faculty = userService.getUserById(request.getRevieweeId());
        if (faculty.getRole() != Role.FACULTY) {
            throw new BadRequestException("Target reviewee must be a faculty member");
        }

        if (feedbackRepository.existsByReviewerIdAndRevieweeIdAndFeedbackType(
                student.getId(), faculty.getId(), FeedbackType.STUDENT_TO_FACULTY)) {
            throw new BadRequestException("You have already submitted feedback for this faculty member (" + faculty.getFullName() + ")");
        }

        Feedback feedback = new Feedback(
                student,
                faculty,
                FeedbackType.STUDENT_TO_FACULTY,
                request.getRating(),
                request.getTeachingQualityRating(),
                request.getCommunicationRating(),
                request.getSubjectKnowledgeRating(),
                null, // participation not applicable for student-to-faculty
                request.getComments()
        );

        Feedback saved = feedbackRepository.save(feedback);
        return mapToResponse(saved);
    }

    @Transactional
    public FeedbackResponse submitFacultyToStudent(String facultyEmail, FeedbackRequest request) {
        User faculty = userService.getUserByEmail(facultyEmail);
        if (faculty.getRole() != Role.FACULTY) {
            throw new BadRequestException("Only faculty members can submit faculty-to-student feedback");
        }

        User student = userService.getUserById(request.getRevieweeId());
        if (student.getRole() != Role.STUDENT) {
            throw new BadRequestException("Target reviewee must be a student");
        }

        Feedback feedback = new Feedback(
                faculty,
                student,
                FeedbackType.FACULTY_TO_STUDENT,
                request.getRating(),
                null, // teaching quality not applicable for student review
                request.getCommunicationRating(),
                request.getSubjectKnowledgeRating(),
                request.getParticipationRating(),
                request.getComments()
        );

        Feedback saved = feedbackRepository.save(feedback);
        return mapToResponse(saved);
    }

    public List<FeedbackResponse> getMySubmissions(String email) {
        User user = userService.getUserByEmail(email);
        return feedbackRepository.findByReviewerIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<FeedbackResponse> getFeedbackReceived(String email) {
        User user = userService.getUserByEmail(email);
        return feedbackRepository.findByRevieweeIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<FeedbackResponse> getAllFeedback() {
        return feedbackRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private FeedbackResponse mapToResponse(Feedback feedback) {
        FeedbackResponse resp = new FeedbackResponse();
        resp.setId(feedback.getId());
        resp.setReviewerId(feedback.getReviewer().getId());
        resp.setReviewerName(feedback.getReviewer().getFullName());
        resp.setReviewerRole(feedback.getReviewer().getRole());
        resp.setRevieweeId(feedback.getReviewee().getId());
        resp.setRevieweeName(feedback.getReviewee().getFullName());
        resp.setRevieweeRole(feedback.getReviewee().getRole());
        resp.setFeedbackType(feedback.getFeedbackType());
        resp.setRating(feedback.getRating());
        resp.setTeachingQualityRating(feedback.getTeachingQualityRating());
        resp.setCommunicationRating(feedback.getCommunicationRating());
        resp.setSubjectKnowledgeRating(feedback.getSubjectKnowledgeRating());
        resp.setParticipationRating(feedback.getParticipationRating());
        resp.setComments(feedback.getComments());
        resp.setCreatedAt(feedback.getCreatedAt());
        return resp;
    }
}
