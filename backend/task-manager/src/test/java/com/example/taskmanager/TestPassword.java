package com.example.taskmanager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPassword {
    public static void main(String[] args) {
        String password = "test123";
        String hash = "$2a$10$GuFhRx5wXE3d3CNCXImYeOJvSHmTT3cyrlFSJhdYGx8UyHwS2JqV6";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(password, hash);
        System.out.println("Password 'test123' matches hash: " + matches);
    }
}