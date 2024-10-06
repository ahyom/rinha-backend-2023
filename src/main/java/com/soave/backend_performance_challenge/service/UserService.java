package com.soave.backend_performance_challenge.service;

import com.soave.backend_performance_challenge.model.entity.UserEntity;
import com.soave.backend_performance_challenge.model.domain.User;
import com.soave.backend_performance_challenge.model.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserEntity userEntity;

    @Autowired
    public UserService(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public User createUser(User user) {
        log.debug("Creating user with id [{}]", user.getId());
        try {
            return userEntity.save(user);
        } catch (Exception e) {
            log.error("Error creating user with id [{}]", user.getId(), e);
            throw new RuntimeException();
        }
    }

    public User getUserById(String userId) {
        log.debug("Retrieving user with id [{}]", userId);
        return userEntity.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " was not found"));
    }

    public long getCountPessoas() {
        log.debug("Retrieving count of pessoas");
        return userEntity.count();
    }
}
