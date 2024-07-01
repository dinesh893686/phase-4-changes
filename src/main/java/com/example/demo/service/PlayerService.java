package com.example.demo.service;

import com.example.demo.dto.PlayerDTO;
import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + id));
        return modelMapper.map(player, PlayerDTO.class);
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = modelMapper.map(playerDTO, Player.class);
        player = playerRepository.save(player);
        return modelMapper.map(player, PlayerDTO.class);
    }

    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + id));

        modelMapper.map(playerDTO, existingPlayer);
        existingPlayer = playerRepository.save(existingPlayer);
        return modelMapper.map(existingPlayer, PlayerDTO.class);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> modelMapper.map(player, PlayerDTO.class))
                .collect(Collectors.toList());
    }
}
