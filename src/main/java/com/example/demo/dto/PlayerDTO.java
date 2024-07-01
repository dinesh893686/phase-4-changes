package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@NoArgsConstructor
@Data
public class PlayerDTO {
    private Long id;
    private String name;
    private int age;
    private String role;

    // Getters and Setters
}

