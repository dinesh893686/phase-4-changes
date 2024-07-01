package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPerformanceDTO {
    private Long id;
    private Long matchId;
    private Long playerId;
    private int runs;
    private int ballsFaced;
    private int wicketsTaken;

    // Getters and Setters
    // Constructors, if needed
}

