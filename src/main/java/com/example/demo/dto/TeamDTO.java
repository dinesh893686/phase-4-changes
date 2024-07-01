package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    private Long id;
    private String name;
    private List<Long> playerIds;

    // Getters and Setters
    // Constructors, if needed
}

