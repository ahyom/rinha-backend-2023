package com.soave.backend_performance_challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(

        @JsonProperty("id")
        String id,

        @NotBlank(message = "Username cannot be blank.")
        @Size(max = 32, message = "Username cannot exceed 32 characters.")
        @JsonProperty("apelido")
        String username,

        @NotBlank(message = "Name cannot be blank.")
        @Size(max = 100, message = "Name cannot exceed 100 characters.")
        @JsonProperty("nome")
        String name,

        @NotNull(message = "Date of birth must be provided.")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in the format yyyy-MM-dd")
        @JsonProperty("nascimento")
        String dateOfBirth,

        @NotNull(message = "Technology stack cannot be null.")
        @JsonProperty("stack")
        List<String> stack
){}
