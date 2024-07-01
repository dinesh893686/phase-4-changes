package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private Long id;
    private Long seriesId;
    private Long team1Id;
    private Long team2Id;
    private Date date;
    private int overs;

    // Getters and Setters
    // Constructors, if needed
}

