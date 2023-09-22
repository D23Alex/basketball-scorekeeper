package com.d23alex.vtbstat.controllers;

import com.d23alex.vtbstat.db.repositories.PlayerRepository;
import com.d23alex.vtbstat.entities.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PlayerController {
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players/{id}")
    Optional<Player> getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id);
    }

}
