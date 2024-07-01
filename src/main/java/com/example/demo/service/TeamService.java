package com.example.demo.service;

import com.example.demo.dto.TeamDTO;
import com.example.demo.model.Team;
import com.example.demo.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TeamDTO getTeamById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + id));
        return modelMapper.map(team, TeamDTO.class);
    }

    public TeamDTO createTeam(TeamDTO teamDTO) {
        Team team = modelMapper.map(teamDTO, Team.class);
        team = teamRepository.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    public TeamDTO updateTeam(Long id, TeamDTO teamDTO) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + id));

        modelMapper.map(teamDTO, existingTeam);
        existingTeam = teamRepository.save(existingTeam);
        return modelMapper.map(existingTeam, TeamDTO.class);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream()
                .map(team -> modelMapper.map(team, TeamDTO.class))
                .collect(Collectors.toList());
    }
}
