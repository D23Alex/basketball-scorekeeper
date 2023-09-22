package com.d23alex.vtbstat.controllers;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.game.GameEvents;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GameEventController {
    private final DatabaseQueries databaseQueries;


    public GameEventController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @GetMapping("/api/events/game/{gameId}")
    Optional<GameEvents> gameEventsByGameId(@PathVariable long gameId) {
        if (databaseQueries.gameExistsById(gameId))
            return Optional.empty();
        return Optional.of(databaseQueries.gameEventsByGameId(gameId));
    }
}
