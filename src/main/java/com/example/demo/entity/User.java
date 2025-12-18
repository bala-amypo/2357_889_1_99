package com.example.demo.entity;
import jakarta.presistence.*;

public class User{
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;


}