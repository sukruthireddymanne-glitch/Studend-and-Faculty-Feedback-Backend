package com.feedback.platform.config;

import com.feedback.platform.entity.Feedback;
import com.feedback.platform.entity.FeedbackType;
import com.feedback.platform.entity.Role;
import com.feedback.platform.entity.User;
import com.feedback.platform.repository.FeedbackRepository;
import com.feedback.platform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           FeedbackRepository feedbackRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        // 1. Seed Faculty Members (Bhanu Chander, Mahesh, Asha, Hariprasad)
        User profBhanuChander = userRepository.save(new User(
                "Bhanu Chander",
                "bhanuchander@college.edu",
                passwordEncoder.encode("faculty123"),
                Role.FACULTY,
                "Computer Science & Engineering"
        ));

        User profMahesh = userRepository.save(new User(
                "Mahesh",
                "mahesh@college.edu",
                passwordEncoder.encode("faculty123"),
                Role.FACULTY,
                "Artificial Intelligence & Machine Learning"
        ));

        User profAsha = userRepository.save(new User(
                "Asha",
                "asha@college.edu",
                passwordEncoder.encode("faculty123"),
                Role.FACULTY,
                "Information Technology"
        ));

        User profHariprasad = userRepository.save(new User(
                "Hariprasad",
                "hariprasad@college.edu",
                passwordEncoder.encode("faculty123"),
                Role.FACULTY,
                "Electronics & Communication Engineering"
        ));

        // 2. Seed Students (Yashwanth Reddy, Deepthi, Madhuri, Sreenija)
        User studentYashwanth = userRepository.save(new User(
                "Yashwanth Reddy",
                "yashwanth@college.edu",
                passwordEncoder.encode("student123"),
                Role.STUDENT,
                "Computer Science & Engineering"
        ));

        User studentDeepthi = userRepository.save(new User(
                "Deepthi",
                "deepthi@college.edu",
                passwordEncoder.encode("student123"),
                Role.STUDENT,
                "Information Technology"
        ));

        User studentMadhuri = userRepository.save(new User(
                "Madhuri",
                "madhuri@college.edu",
                passwordEncoder.encode("student123"),
                Role.STUDENT,
                "Artificial Intelligence & Data Science"
        ));

        User studentSreenija = userRepository.save(new User(
                "Sreenija",
                "sreenija@college.edu",
                passwordEncoder.encode("student123"),
                Role.STUDENT,
                "Computer Science & Engineering"
        ));

        // 3. Seed Initial Feedback (Student-to-Faculty)
        feedbackRepository.save(new Feedback(
                studentYashwanth,
                profBhanuChander,
                FeedbackType.STUDENT_TO_FACULTY,
                5,
                5,
                5,
                5,
                null,
                "Prof. Bhanu Chander explains complex data structures and algorithms with crystal clarity. Highly engaging lectures!"
        ));

        feedbackRepository.save(new Feedback(
                studentDeepthi,
                profMahesh,
                FeedbackType.STUDENT_TO_FACULTY,
                5,
                5,
                5,
                5,
                null,
                "Prof. Mahesh provides excellent hands-on machine learning projects and great guidance during lab sessions."
        ));

        feedbackRepository.save(new Feedback(
                studentMadhuri,
                profAsha,
                FeedbackType.STUDENT_TO_FACULTY,
                4,
                4,
                5,
                5,
                null,
                "Prof. Asha gives in-depth web application architecture explanations and very helpful code reviews."
        ));

        feedbackRepository.save(new Feedback(
                studentSreenija,
                profHariprasad,
                FeedbackType.STUDENT_TO_FACULTY,
                5,
                5,
                4,
                5,
                null,
                "Prof. Hariprasad makes circuit design and microprocessors enjoyable and easy to grasp."
        ));

        // 4. Seed Initial Feedback (Faculty-to-Student Mentoring Evaluations)
        feedbackRepository.save(new Feedback(
                profBhanuChander,
                studentYashwanth,
                FeedbackType.FACULTY_TO_STUDENT,
                5,
                null,
                5,
                5,
                5,
                "Yashwanth consistently demonstrates exceptional analytical thinking and active participation in class discussions."
        ));

        feedbackRepository.save(new Feedback(
                profMahesh,
                studentDeepthi,
                FeedbackType.FACULTY_TO_STUDENT,
                5,
                null,
                5,
                5,
                5,
                "Deepthi shows great dedication to projects, submit assignments on time, and collaborates effectively in teams."
        ));

        feedbackRepository.save(new Feedback(
                profAsha,
                studentMadhuri,
                FeedbackType.FACULTY_TO_STUDENT,
                4,
                null,
                4,
                4,
                5,
                "Madhuri is very attentive, grasps software design principles quickly, and performs well in lab tests."
        ));

        feedbackRepository.save(new Feedback(
                profHariprasad,
                studentSreenija,
                FeedbackType.FACULTY_TO_STUDENT,
                5,
                null,
                5,
                5,
                5,
                "Sreenija displays strong problem-solving skills and great leadership during practical workshop assignments."
        ));
    }
}
