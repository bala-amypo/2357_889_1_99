package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public User registerUser(Map<String, String> userData) {

        String email = userData.get("email");
        String password = userData.get("password");

        // ✅ FIX 1: Required field validation
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email required");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password required");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();

        // ✅ FIX 2: Default name if missing
        user.setName(
            userData.getOrDefault("name", "Test User")
        );

        user.setEmail(email);

        // ✅ FIX 3: Password must be encoded
        user.setPassword(encoder.encode(password));

        // ✅ FIX 4: Correct ROLE name
        Optional<Role> roleOpt = roleRepository.findByName("ROLE_USER");

        Role userRole = roleOpt.orElseGet(() -> {
            Role role = new Role();
            role.setName("ROLE_USER");
            return roleRepository.save(role);
        });

        user.getRoles().add(userRole);

        return userRepository.save(user);
    }
}
