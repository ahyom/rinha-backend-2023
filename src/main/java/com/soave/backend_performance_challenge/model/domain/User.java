package com.soave.backend_performance_challenge.model.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "tb_users")
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "apelido", nullable = false, unique = true)
    private String username;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "nascimento", nullable = false)
    private String dateOfBirth;

    @ElementCollection
    @CollectionTable(name = "user_stack", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "stack")
    private List<String> stack;

    public User() {}

    public User(UUID id, String username, String name, String dateOfBirth, List<String> stack) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.stack = stack;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }
}
