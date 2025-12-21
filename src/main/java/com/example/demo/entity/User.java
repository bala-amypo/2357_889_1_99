package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {}

    public User(String name, String email, String password, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
}
