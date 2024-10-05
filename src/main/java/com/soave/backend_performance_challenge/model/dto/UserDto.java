package com.soave.backend_performance_challenge.model.domain;

import java.util.List;

public record User(
        String userName,
        String name,
        String dateOfBirth,
        List<String> stack
){}
