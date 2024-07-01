package com.example.demo.service;

import com.example.demo.dto.MatchDTO;
import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.repository.MatchRepository;
import com.example.demo.repository.PlayerPerformanceRepository;
import com.example.demo.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerPerformanceRepository playerPerformanceRepository;

    @Transactional
    public void startMatch(Long team1Id, Long team2Id, int overs) {
        Team team1 = teamRepository.findById(team1Id).orElseThrow(() -> new RuntimeException("Team not found"));
        Team team2 = teamRepository.findById(team2Id).orElseThrow(() -> new RuntimeException("Team not found"));

        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setOvers(overs);
        match.setDate(new Date());

        match = matchRepository.save(match);

        simulateMatch(match);
    }

    private void simulateMatch(Match match) {
        Team team1 = match.getTeam1();
        Team team2 = match.getTeam2();

        simulateInnings(match, team1, team2);
        simulateInnings(match, team2, team1);

        determineWinner(match, team1, team2);
    }

    private void simulateInnings(Match match, Team battingTeam, Team bowlingTeam) {
        Random random = new Random();
        int totalOvers = match.getOvers();
        int score = 0;
        int wickets = 0;

        for (int over = 0; over < totalOvers; over++) {
            for (int ball = 0; ball < 6; ball++) {
                int outcome = random.nextInt(8); // 0-6 runs or Wicket (7)

                if (outcome == 7) { // Wicket
                    wickets++;
                    if (wickets == 10) {
                        break;
                    }
                } else { // Runs
                    score += outcome;
                }
            }
        }

        if (battingTeam.getId().equals(match.getTeam1().getId())) {
            match.setTeam1Score(score);
            match.setTeam1Wickets(wickets);
        } else {
            match.setTeam2Score(score);
            match.setTeam2Wickets(wickets);
        }

        matchRepository.save(match);
    }

    private void determineWinner(Match match, Team team1, Team team2) {
        if (match.getTeam1Score() > match.getTeam2Score()) {
            match.setWinner(team1);
        } else if (match.getTeam1Score() < match.getTeam2Score()) {
            match.setWinner(team2);
        } else {
            match.setWinner(null); // Draw
        }

        matchRepository.save(match);
    }

    public MatchDTO getMatchById(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match not found with id: " + id));
        return modelMapper.map(match, MatchDTO.class);
    }

    public MatchDTO createMatch(MatchDTO matchDTO) {
        Match match = modelMapper.map(matchDTO, Match.class);
        match = matchRepository.save(match);
        return modelMapper.map(match, MatchDTO.class);
    }

    public MatchDTO updateMatch(Long id, MatchDTO matchDTO) {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match not found with id: " + id));

        modelMapper.map(matchDTO, existingMatch);
        existingMatch = matchRepository.save(existingMatch);
        return modelMapper.map(existingMatch, MatchDTO.class);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    public List<MatchDTO> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        return matches.stream()
                .map(match -> modelMapper.map(match, MatchDTO.class))
                .collect(Collectors.toList());
    }


}
