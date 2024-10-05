package com.soave.backend_performance_challenge.model.mapper;

import com.soave.backend_performance_challenge.model.domain.User;
import com.soave.backend_performance_challenge.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public User toEntity(UserDto userDto) {
        User user = new User();

        if (userDto.id() == null) {
            user.setId(UUID.randomUUID());
        } else {
            user.setId(UUID.fromString(userDto.id()));
        }

        user.setUsername(userDto.username());
        user.setName(userDto.name());
        user.setDateOfBirth(userDto.dateOfBirth());
        user.setStack(userDto.stack());

        return user;
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId().toString(),
                user.getUsername(),
                user.getName(),
                user.getDateOfBirth(),
                user.getStack()
        );
    }
}
