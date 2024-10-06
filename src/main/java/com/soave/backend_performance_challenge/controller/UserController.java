package com.soave.backend_performance_challenge.controller;

import com.soave.backend_performance_challenge.service.UserService;
import com.soave.backend_performance_challenge.model.domain.User;
import com.soave.backend_performance_challenge.model.dto.UserDto;
import com.soave.backend_performance_challenge.model.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pessoas")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        var userMapped = userMapper.toEntity(userDto);

        User userCreated = userService.createUser(userMapped);

        URI location = URI.create("/pessoas/" + userCreated.getId());
        return ResponseEntity.created(location).body(userMapper.toDto(userCreated));
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("user-id") String userId) {
        User foundedUser = userService.getUserById(userId);
        return ResponseEntity.ok(userMapper.toDto(foundedUser));
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<Long> getCountPessoas() {
        return ResponseEntity.ok(userService.getCountPessoas());
    }
}
