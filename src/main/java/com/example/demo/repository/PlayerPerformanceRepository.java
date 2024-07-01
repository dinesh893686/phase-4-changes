package com.example.demo.repository;

import com.example.demo.model.PlayerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerPerformanceRepository extends JpaRepository<PlayerPerformance, Long> {
    Optional<PlayerPerformance> findByMatchIdAndPlayerId(Long matchId, Long playerId);
}
