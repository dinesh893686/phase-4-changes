package com.example.demo.controller;

import com.example.demo.dto.PlayerPerformanceDTO;
import com.example.demo.service.PlayerPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player-performances")
public class PlayerPerformanceController {

    @Autowired
    private PlayerPerformanceService playerPerformanceService;

    @GetMapping("/{id}")
    public ResponseEntity<PlayerPerformanceDTO> getPlayerPerformanceById(@PathVariable Long id) {
        PlayerPerformanceDTO playerPerformanceDTO = playerPerformanceService.getPlayerPerformanceById(id);
        return ResponseEntity.ok().body(playerPerformanceDTO);
    }

    @PostMapping
    public ResponseEntity<PlayerPerformanceDTO> createPlayerPerformance(@RequestBody PlayerPerformanceDTO playerPerformanceDTO) {
        PlayerPerformanceDTO createdPlayerPerformanceDTO = playerPerformanceService.createPlayerPerformance(playerPerformanceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayerPerformanceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerPerformanceDTO> updatePlayerPerformance(@PathVariable Long id, @RequestBody PlayerPerformanceDTO playerPerformanceDTO) {
        PlayerPerformanceDTO updatedPlayerPerformanceDTO = playerPerformanceService.updatePlayerPerformance(id, playerPerformanceDTO);
        return ResponseEntity.ok().body(updatedPlayerPerformanceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerPerformance(@PathVariable Long id) {
        playerPerformanceService.deletePlayerPerformance(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PlayerPerformanceDTO>> getAllPlayerPerformances() {
        List<PlayerPerformanceDTO> playerPerformances = playerPerformanceService.getAllPlayerPerformances();
        return ResponseEntity.ok().body(playerPerformances);
    }
}
