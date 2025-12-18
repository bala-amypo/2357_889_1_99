package com.example.demo.entity;

public class Role{
    @Id
    private Long id;
    @Column(unique=true)
    private String name;
}