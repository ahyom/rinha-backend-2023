package com.soave.backend_performance_challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soave.backend_performance_challenge.UserService;
import com.soave.backend_performance_challenge.model.domain.User;
import com.soave.backend_performance_challenge.model.dto.UserDto;
import com.soave.backend_performance_challenge.model.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final String BASE_URL = "/pessoas";
    private static final String ID = "46d4d866-ec12-43fd-b513-7d5799ef05de";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    private UserDto userDto;

    private User user;

    @BeforeEach
    void setup() {
        userDto = buildDefaultUserDto();
        user = buildDefaultUser();
    }

    @Test
    void whenAllAttributesAreCorrect_shouldReturnHttp200WithId() throws Exception {

        when(userMapper.toEntity(any(UserDto.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "/pessoas/" + ID));

        verify(userService).createUser(user);
    }

    private UserDto buildDefaultUserDto() {
        List<String> stacks = List.of("Java", "Kotlin", "MySQL");
        return new UserDto(
                ID,
                "sikamikaniko",
                "Arthur",
                "1993-08-23",
                stacks
        );
    }

    private User buildDefaultUser() {
        List<String> stacks = List.of("Java", "Kotlin", "MySQL");
        return new User(
                UUID.fromString(ID),
                "sikamikaniko",
                "Arthur",
                "1993-08-23",
                stacks
        );
    }
}
