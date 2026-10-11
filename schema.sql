-- ==========================================================
-- Student and Faculty Feedback Platform - Database Schema
-- Compatible with H2 and standard SQL databases
-- ==========================================================

-- 1. Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    department VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- 2. Feedback Table
CREATE TABLE IF NOT EXISTS feedback (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reviewer_id BIGINT NOT NULL,
    reviewee_id BIGINT NOT NULL,
    feedback_type VARCHAR(30) NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    teaching_quality_rating INT CHECK (teaching_quality_rating BETWEEN 1 AND 5),
    communication_rating INT CHECK (communication_rating BETWEEN 1 AND 5),
    subject_knowledge_rating INT CHECK (subject_knowledge_rating BETWEEN 1 AND 5),
    participation_rating INT CHECK (participation_rating BETWEEN 1 AND 5),
    comments CLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_feedback_reviewer FOREIGN KEY (reviewer_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_feedback_reviewee FOREIGN KEY (reviewee_id) REFERENCES users (id) ON DELETE CASCADE
);
