package com.d23alex.vtbstat.controllers;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entities.Player;
import com.d23alex.vtbstat.entities.gameevents.LineupOccurrence;
import com.d23alex.vtbstat.game.GameEventLog;
import com.d23alex.vtbstat.game.stats.PlayerGameStatLine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StatController {
    private final DatabaseQueries databaseQueries;

    public StatController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @GetMapping("/api/stats/playergamestats/{playerId}/{gameId}")
    Optional<PlayerGameStatLine> playerGameStatLine(@PathVariable Long playerId, @PathVariable Long gameId) {
        Optional<Player> player = databaseQueries.playerById(playerId);
        if (player.isEmpty())
            return Optional.empty();
        Optional<GameEventLog> gameEventLog = databaseQueries.gameEventsByGameId(gameId);
        if (gameEventLog.isEmpty())
            return Optional.empty();
        return Optional.of(new PlayerGameStatLine(player.get(), gameEventLog.get()));
    }

    @GetMapping("/api/stats/boxscore/{gameId}")
    Optional<List<PlayerGameStatLine>> playerGameStatLine(@PathVariable Long gameId) {
        var gameEventLog = databaseQueries.gameEventsByGameId(gameId);
        if (gameEventLog.isEmpty())
            return Optional.empty();
        var players = gameEventLog.get().getLineupOccurrences().stream().map(LineupOccurrence::getPlayer);
        return Optional.of(players.map(player -> new PlayerGameStatLine(player, gameEventLog.get())).toList());
    }
}
