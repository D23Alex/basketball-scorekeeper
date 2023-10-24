package com.d23alex.vtbstat.restapi.controllers;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entities.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Optional;

@RestController
public class PlayerController {
    private final DatabaseQueries databaseQueries;

    public PlayerController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @GetMapping("/api/players/{id}")
    Optional<Player> getPlayerById(@PathVariable Long id) {
        return databaseQueries.playerById(id);
    }

    @GetMapping("/api/players/byteam/{teamId}/{date}")
    Iterable<Player> playersOfTeamByDate(@PathVariable Long teamId, @PathVariable Date date) {
        return databaseQueries.teamMembersByDate(teamId, date);
    }
}
