package com.sparta.commerce.user.repository;

import com.sparta.commerce.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String encodedEmail);

  Optional<User> findByUsername(String username);
}
