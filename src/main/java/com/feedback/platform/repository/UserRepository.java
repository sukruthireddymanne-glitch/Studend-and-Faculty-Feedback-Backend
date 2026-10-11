package com.feedback.platform.repository;

import com.feedback.platform.entity.Role;
import com.feedback.platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByRoleOrderByFullNameAsc(Role role);

    long countByRole(Role role);
}
