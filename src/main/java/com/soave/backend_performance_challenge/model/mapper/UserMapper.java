package com.soave.backend_performance_challenge.model.mapper;

import com.soave.backend_performance_challenge.model.domain.User;
import com.soave.backend_performance_challenge.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class UserMapper {

    public User toEntity(UserDto userDto) {
        User user = new User();
        String userId = userDto.id();

        if (isNull(userId)) {
            userId = UUID.randomUUID().toString();
        }

        user.setId(UUID.fromString(userId));
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

    public List<UserDto> toDTOList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
