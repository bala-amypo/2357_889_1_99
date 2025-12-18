package com.example.demo.entity;
import jakarta.persistence.*;

public class Role{
    @Id
    private Long id;
    @Column(unique=true)
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role() {
    }
    
}