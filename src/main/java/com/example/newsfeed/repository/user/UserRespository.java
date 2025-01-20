package com.example.newsfeed.repository.user;

import com.example.newsfeed.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findUserByUserName(String userName);

    boolean existsByEmail(String email);
}
