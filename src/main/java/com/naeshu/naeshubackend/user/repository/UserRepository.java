package com.naeshu.naeshubackend.user.repository;

import com.naeshu.naeshubackend.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
}
