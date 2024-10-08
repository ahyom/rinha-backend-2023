package com.soave.backend_performance_challenge.model.entity;

import com.soave.backend_performance_challenge.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserEntity extends JpaRepository<User, UUID> {

    List<User> findByStackContaining(String stack);
}
