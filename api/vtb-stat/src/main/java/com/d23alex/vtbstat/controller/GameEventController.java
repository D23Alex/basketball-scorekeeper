package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.payload.statistics.GameEventLog;
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
    Optional<GameEventLog> gameEventsByGameId(@PathVariable long gameId) {
        if (databaseQueries.isGameExistsById(gameId))
            return databaseQueries.gameEventsByGameId(gameId);
        return Optional.empty();
    }
}
