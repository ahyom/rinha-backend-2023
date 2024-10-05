package com.soave.backend_performance_challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soave.backend_performance_challenge.model.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String BASE_URL = "/users";

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private UserDto userDto;

    private static final String ID = "46d4d866-ec12-43fd-b513-7d5799ef05de";

    @Autowired
    public UserControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setup() {
        userDto = buildDeafultUserDto();
    }

    @Test
    void whenAllAttributesAreCorrect_shouldReturnHttp200WithId() throws Exception {
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    private UserDto buildDeafultUserDto() {
        List<String> stacks = List.of("Java", "Kotlin", "MySQL");
        return new UserDto(
                ID,
                "sikamikaniko",
                "Arthur",
                "1993-08-23",
                stacks
        );
    }
}
