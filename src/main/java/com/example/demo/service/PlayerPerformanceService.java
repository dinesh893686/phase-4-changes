package com.example.demo.service;

import com.example.demo.dto.PlayerPerformanceDTO;
import com.example.demo.model.PlayerPerformance;
import com.example.demo.repository.PlayerPerformanceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerPerformanceService {

    @Autowired
    private PlayerPerformanceRepository playerPerformanceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PlayerPerformanceDTO getPlayerPerformanceById(Long id) {
        PlayerPerformance playerPerformance = playerPerformanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PlayerPerformance not found with id: " + id));
        return modelMapper.map(playerPerformance, PlayerPerformanceDTO.class);
    }

    public PlayerPerformanceDTO createPlayerPerformance(PlayerPerformanceDTO playerPerformanceDTO) {
        PlayerPerformance playerPerformance = modelMapper.map(playerPerformanceDTO, PlayerPerformance.class);
        playerPerformance = playerPerformanceRepository.save(playerPerformance);
        return modelMapper.map(playerPerformance, PlayerPerformanceDTO.class);
    }

    public PlayerPerformanceDTO updatePlayerPerformance(Long id, PlayerPerformanceDTO playerPerformanceDTO) {
        PlayerPerformance existingPlayerPerformance = playerPerformanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PlayerPerformance not found with id: " + id));

        modelMapper.map(playerPerformanceDTO, existingPlayerPerformance);
        existingPlayerPerformance = playerPerformanceRepository.save(existingPlayerPerformance);
        return modelMapper.map(existingPlayerPerformance, PlayerPerformanceDTO.class);
    }

    public void deletePlayerPerformance(Long id) {
        playerPerformanceRepository.deleteById(id);
    }

    public List<PlayerPerformanceDTO> getAllPlayerPerformances() {
        return playerPerformanceRepository.findAll().stream()
                .map(playerPerformance -> modelMapper.map(playerPerformance, PlayerPerformanceDTO.class))
                .collect(Collectors.toList());
    }
}
