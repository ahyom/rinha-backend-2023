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

import static org.hamcrest.Matchers.matchesPattern;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final String BASE_URL = "/pessoas";

    private static final String ID = "46d4d866-ec12-43fd-b513-7d5799ef05de";

    private static final String LOCATION_HEADER = "Location";

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
        when(userMapper.toEntity(any(UserDto.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);
    }

    @Test
    void whenAllAttributesAreCorrect_shouldReturnHttp200WithId() throws Exception {
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
        )
        .andExpect(status().isCreated())
        .andExpect(header().exists(LOCATION_HEADER))
        .andExpect(header().string(
                LOCATION_HEADER,
                matchesPattern("/pessoas/[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}")));

        verify(userService).createUser(user);
        verify(userMapper).toEntity(userDto);
        verify(userMapper, never()).toDto(user);
    }

    @Test
    void whenApelidoIsNotInformed_shouldReturnHttp422() throws Exception {
        userDto = new UserDto(null, null, "Arthur", "1993-08-23", List.of("Java", "Kotlin", "MySQL"));

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void whenApelidoIsGreaterThan32Bytes_shouldReturnHttp422() throws Exception {
        String longUsername = "a".repeat(33); // 33 characters
        userDto = new UserDto(null, longUsername, "Arthur", "1993-08-23", List.of("Java", "Kotlin", "MySQL"));

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void whenApelidoIsNotAString_shouldReturnHttp400() throws Exception {
        String invalidJson = "{ \"apelido\": 123, \"nome\": \"Arthur\", \"nascimento\": \"1993-08-23\", \"stack\": [\"Java\"] }";

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invalidJson)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenNomeIsNotInformed_shouldReturnHttp422() throws Exception {
        userDto = new UserDto(null, "sikamikaniko", null, "1993-08-23", List.of("Java", "Kotlin", "MySQL"));

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void whenNomeIsGreaterThan100Bytes_shouldReturnHttp422() throws Exception {
        String longName = "a".repeat(101); // 101 characters
        userDto = new UserDto(null, "sikamikaniko", longName, "1993-08-23", List.of("Java", "Kotlin", "MySQL"));

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void whenNomeIsNotAString_shouldReturnHttp400() throws Exception {
        String invalidJson = "{ \"apelido\": \"sikamikaniko\", \"nome\": 123, \"nascimento\": \"1993-08-23\", \"stack\": [\"Java\"] }";

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invalidJson)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenNascimentoIsNotInformed_shouldReturnHttp422() throws Exception {
        userDto = new UserDto(null, "sikamikaniko", "Arthur", null, List.of("Java", "Kotlin", "MySQL"));

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void whenNascimentoIsNotOnYYYY_MM_DD_format_shouldReturnHttp422() throws Exception {
        userDto = new UserDto(null, "sikamikaniko", "Arthur", "23-08-1993", List.of("Java", "Kotlin", "MySQL"));

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isUnprocessableEntity());
    }

    private UserDto buildDefaultUserDto() {
        List<String> stacks = List.of("Java", "Kotlin", "MySQL");
        return new UserDto(
                null,
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
