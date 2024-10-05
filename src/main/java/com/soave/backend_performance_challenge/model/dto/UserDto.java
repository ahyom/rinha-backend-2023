package com.soave.backend_performance_challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(

        @JsonProperty("id")
        String id,

        @JsonProperty("apelido")
        String username,

        @JsonProperty("nome")
        String name,

        @JsonProperty("nascimento")
        String dateOfBirth,

        @JsonProperty("stack")
        List<String> stack
){}
