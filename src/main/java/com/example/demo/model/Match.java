package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series_id")
    private Long seriesId;

    @ManyToOne
    @JoinColumn(name = "team1_id", referencedColumnName = "id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", referencedColumnName = "id")
    private Team team2;

    private Date date;

    private int overs;
    private int team1Score;
    private int team1Wickets;
    private int team2Score;
    private int team2Wickets;

    @ManyToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private Team winner;

    @OneToMany(mappedBy = "match")
    private List<PlayerPerformance> playerPerformances;
    // Getters and Setters
}
