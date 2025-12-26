package com.example.demo.service;
import com.example.demo.entity.User;
import java.util.Map;
public interface UserService {
    User registerUser(Map<String, String> userData);
}
