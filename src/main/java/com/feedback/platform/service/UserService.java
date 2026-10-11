package com.feedback.platform.service;

import com.feedback.platform.dto.*;
import com.feedback.platform.entity.Role;
import com.feedback.platform.entity.User;
import com.feedback.platform.exception.BadRequestException;
import com.feedback.platform.exception.ResourceNotFoundException;
import com.feedback.platform.repository.UserRepository;
import com.feedback.platform.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("An account with this email already exists: " + request.getEmail());
        }

        User user = new User(
                request.getFullName(),
                request.getEmail().toLowerCase().trim(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole(),
                request.getDepartment() != null ? request.getDepartment().trim() : "General"
        );

        User savedUser = userRepository.save(user);
        String token = jwtTokenProvider.generateToken(savedUser.getEmail(), savedUser.getId(), savedUser.getRole().name());

        return new AuthResponse(
                token,
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getDepartment()
        );
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail().toLowerCase().trim())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getId(), user.getRole().name());

        return new AuthResponse(
                token,
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getDepartment()
        );
    }

    public UserProfileDto getProfile(String email) {
        User user = getUserByEmail(email);
        return mapToProfileDto(user);
    }

    @Transactional
    public UserProfileDto updateProfile(String email, UpdateProfileRequest request) {
        User user = getUserByEmail(email);
        user.setFullName(request.getFullName().trim());
        if (request.getDepartment() != null && !request.getDepartment().isBlank()) {
            user.setDepartment(request.getDepartment().trim());
        }
        User updated = userRepository.save(user);
        return mapToProfileDto(updated);
    }

    @Transactional
    public void changePassword(String email, ChangePasswordRequest request) {
        User user = getUserByEmail(email);
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Incorrect old password");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public List<UserProfileDto> getStudents() {
        return userRepository.findByRoleOrderByFullNameAsc(Role.STUDENT)
                .stream()
                .map(this::mapToProfileDto)
                .collect(Collectors.toList());
    }

    public List<UserProfileDto> getFaculty() {
        return userRepository.findByRoleOrderByFullNameAsc(Role.FACULTY)
                .stream()
                .map(this::mapToProfileDto)
                .collect(Collectors.toList());
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    private UserProfileDto mapToProfileDto(User user) {
        return new UserProfileDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getDepartment(),
                user.getCreatedAt()
        );
    }
}
