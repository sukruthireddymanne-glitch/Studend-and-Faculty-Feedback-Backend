# Student and Faculty Feedback Platform – Backend API

RESTful API backend for the **Student and Faculty Feedback Platform** built using Java 17+, Spring Boot 3, Spring Security (JWT), Spring Data JPA, and MySQL.

---

## 🛠️ Tech Stack
- **Language**: Java 17+
- **Framework**: Spring Boot 3.3.4
- **Security**: Spring Security 6 (JWT + BCrypt)
- **ORM / Persistence**: Spring Data JPA, Hibernate
- **Database**: MySQL 8.0
- **Build Tool**: Apache Maven

---

## 📁 Package Architecture
```
src/main/java/com/feedback/platform/
├── FeedbackPlatformApplication.java    # Spring Boot entry point
├── config/
│   └── DataInitializer.java           # Initial data seeder (Students & Faculty)
├── controller/
│   ├── AuthController.java            # Login, register, logout
│   ├── UserController.java            # Profiles, student & faculty directories
│   ├── FeedbackController.java        # Submit and fetch reviews
│   └── AnalyticsController.java       # High-level ratings & category analytics
├── dto/                               # Request/response DTOs
├── entity/                            # JPA entities (User, Feedback, Role, FeedbackType)
├── exception/                         # Global exception handling & custom exceptions
├── repository/                        # Spring Data JPA repositories
├── security/                          # JWT Token provider, filter & security config
└── service/                           # Business logic layer
```

---

## ⚙️ Setup & Execution
1. Ensure MySQL is running on `localhost:3306` with credentials `root` / `root`.
2. Run using Maven:
   ```bash
   mvn spring-boot:run
   ```
3. API runs on `http://localhost:8080`.
