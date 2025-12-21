package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    public Role() {}
    public Role(String name) {
        this.name = name;
    }

    // getters & setters
}
