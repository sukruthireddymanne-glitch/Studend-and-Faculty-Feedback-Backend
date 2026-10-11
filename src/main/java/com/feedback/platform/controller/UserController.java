package com.feedback.platform.controller;

import com.feedback.platform.dto.ApiResponse;
import com.feedback.platform.dto.ChangePasswordRequest;
import com.feedback.platform.dto.UpdateProfileRequest;
import com.feedback.platform.dto.UserProfileDto;
import com.feedback.platform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileDto>> getProfile(@AuthenticationPrincipal String email) {
        UserProfileDto profile = userService.getProfile(email);
        return ResponseEntity.ok(ApiResponse.ok("Profile retrieved successfully", profile));
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileDto>> updateProfile(
            @AuthenticationPrincipal String email,
            @Valid @RequestBody UpdateProfileRequest request) {
        UserProfileDto updated = userService.updateProfile(email, request);
        return ResponseEntity.ok(ApiResponse.ok("Profile updated successfully", updated));
    }

    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @AuthenticationPrincipal String email,
            @Valid @RequestBody ChangePasswordRequest request) {
        userService.changePassword(email, request);
        return ResponseEntity.ok(ApiResponse.ok("Password changed successfully"));
    }

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<UserProfileDto>>> getStudents() {
        List<UserProfileDto> students = userService.getStudents();
        return ResponseEntity.ok(ApiResponse.ok("Students retrieved successfully", students));
    }

    @GetMapping("/faculty")
    public ResponseEntity<ApiResponse<List<UserProfileDto>>> getFaculty() {
        List<UserProfileDto> faculty = userService.getFaculty();
        return ResponseEntity.ok(ApiResponse.ok("Faculty members retrieved successfully", faculty));
    }
}
