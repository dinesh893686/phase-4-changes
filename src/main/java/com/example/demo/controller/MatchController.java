package com.example.demo.controller;

import com.example.demo.dto.MatchDTO;
import com.example.demo.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        MatchDTO matchDTO = matchService.getMatchById(id);
        return ResponseEntity.ok().body(matchDTO);
    }

    @PostMapping("/start")
    public ResponseEntity<String> startMatch(@RequestBody MatchDTO matchDTO) {
        matchService.startMatch(matchDTO.getTeam1Id(), matchDTO.getTeam2Id(), matchDTO.getOvers());
        return ResponseEntity.ok("Match started successfully");
    }

    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO matchDTO) {
        MatchDTO createdMatchDTO = matchService.createMatch(matchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatchDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable Long id, @RequestBody MatchDTO matchDTO) {
        MatchDTO updatedMatchDTO = matchService.updateMatch(id, matchDTO);
        return ResponseEntity.ok().body(updatedMatchDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        List<MatchDTO> matches = matchService.getAllMatches();
        return ResponseEntity.ok().body(matches);
    }
}
