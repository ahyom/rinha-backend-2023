package com.soave.backend_performance_challenge.service;

import com.soave.backend_performance_challenge.model.entity.UserEntity;
import com.soave.backend_performance_challenge.model.domain.User;
import com.soave.backend_performance_challenge.model.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserEntity userEntity;

    @Autowired
    public UserService(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public User createUser(User user) {
        try {
            return userEntity.save(user);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public User getUserById(String userId) {
        return userEntity.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " was not found"));
    }
}
