package com.feedback.platform.controller;

import com.feedback.platform.dto.ApiResponse;
import com.feedback.platform.dto.FeedbackRequest;
import com.feedback.platform.dto.FeedbackResponse;
import com.feedback.platform.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/student-to-faculty")
    public ResponseEntity<ApiResponse<FeedbackResponse>> submitStudentToFaculty(
            @AuthenticationPrincipal String email,
            @Valid @RequestBody FeedbackRequest request) {
        FeedbackResponse response = feedbackService.submitStudentToFaculty(email, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Feedback submitted successfully", response));
    }

    @PostMapping("/faculty-to-student")
    public ResponseEntity<ApiResponse<FeedbackResponse>> submitFacultyToStudent(
            @AuthenticationPrincipal String email,
            @Valid @RequestBody FeedbackRequest request) {
        FeedbackResponse response = feedbackService.submitFacultyToStudent(email, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Feedback submitted successfully", response));
    }

    @GetMapping("/my-submissions")
    public ResponseEntity<ApiResponse<List<FeedbackResponse>>> getMySubmissions(
            @AuthenticationPrincipal String email) {
        List<FeedbackResponse> list = feedbackService.getMySubmissions(email);
        return ResponseEntity.ok(ApiResponse.ok("Submissions retrieved successfully", list));
    }

    @GetMapping("/received")
    public ResponseEntity<ApiResponse<List<FeedbackResponse>>> getReceivedFeedback(
            @AuthenticationPrincipal String email) {
        List<FeedbackResponse> list = feedbackService.getFeedbackReceived(email);
        return ResponseEntity.ok(ApiResponse.ok("Received feedback retrieved successfully", list));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<FeedbackResponse>>> getAllFeedback() {
        List<FeedbackResponse> list = feedbackService.getAllFeedback();
        return ResponseEntity.ok(ApiResponse.ok("All feedback retrieved successfully", list));
    }
}
