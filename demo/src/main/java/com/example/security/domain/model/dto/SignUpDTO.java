package com.example.security.domain.model.dto;

public record SignUpDTO(String name,
                        String lastName,
                        String email,
                        String password) {}