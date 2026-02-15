package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * User Registration Endpoint
     * POST /api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email already registered");
            return ResponseEntity.badRequest().body(response);
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user
        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("email", user.getEmail());
        response.put("firstName", user.getFirstName() != null ? user.getFirstName() : "");
        response.put("lastName", user.getLastName() != null ? user.getLastName() : "");

        return ResponseEntity.ok(response);
    }

    /**
     * User Login Endpoint
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Find user by email
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.badRequest().body(response);
        }

        // Check password
        User dbUser = existingUser.get();
        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", "LOGIN_SUCCESS_TOKEN");
            response.put("email", dbUser.getEmail());
            response.put("userId", dbUser.getId());
            response.put("firstName", dbUser.getFirstName() != null ? dbUser.getFirstName() : "");
            response.put("lastName", dbUser.getLastName() != null ? dbUser.getLastName() : "");
            response.put("createdAt", dbUser.getCreatedAt() != null ? dbUser.getCreatedAt().toString() : "");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    /**
     * User Logout Endpoint
     * POST /api/auth/logout
     * 
     * Note: Since we're using token-based auth without JWT,
     * the actual token invalidation is handled client-side.
     * This endpoint provides a consistent API for logout.
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        Map<String, Object> response = new HashMap<>();
        
        // In a full JWT implementation, you would:
        // 1. Extract the token from Authorization header
        // 2. Add it to a blacklist in Redis/database
        // 3. Return success
        
        // For this implementation, we just acknowledge the logout request
        response.put("message", "Logout successful");
        response.put("loggedOut", true);
        
        return ResponseEntity.ok(response);
    }
}
