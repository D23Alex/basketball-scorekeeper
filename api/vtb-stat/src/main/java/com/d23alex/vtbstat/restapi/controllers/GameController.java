package com.d23alex.vtbstat.restapi.controllers;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entities.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GameController {
    private final DatabaseQueries databaseQueries;

    public GameController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @GetMapping("/api/game/{id}")
    Optional<Game> getGameById(@PathVariable Long id) {
        return databaseQueries.gameById(id);
    }
}
