package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import com.d23alex.vtbstat.model.Team;
import com.d23alex.vtbstat.model.gameevents.LineupOccurrence;
import com.d23alex.vtbstat.payload.statistics.GameEventLog;
import com.d23alex.vtbstat.payload.statistics.Performance;
import com.d23alex.vtbstat.payload.statistics.PlayerGameStatLine;
import com.d23alex.vtbstat.payload.performance.PlayerOverTimePerformance;
import com.d23alex.vtbstat.payload.performance.PlayerSingleGamePerformance;
import com.d23alex.vtbstat.payload.performance.TeamOverTimePerformance;
import com.d23alex.vtbstat.payload.performance.TeamSingleGamePerformance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class StatController {
    private final DatabaseQueries databaseQueries;

    public StatController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @GetMapping("/api/stats/playergamestats/{playerId}/{gameId}")
    Optional<PlayerGameStatLine> playerGameStatLine(@PathVariable Long playerId, @PathVariable Long gameId) {
        Optional<Player> player = databaseQueries.getPlayerById(playerId);
        if (player.isEmpty())
            return Optional.empty();
        Optional<GameEventLog> gameEventLog = databaseQueries.gameEventsByGameId(gameId);
        if (gameEventLog.isEmpty())
            return Optional.empty();
        return Optional.of(new PlayerGameStatLine(player.get(), gameEventLog.get()));
    }

//    @GetMapping("/api/stats/boxscore/{gameId}")
//    Optional<List<PlayerGameStatLine>> playerGameStatLine(@PathVariable Long gameId) {
//        var gameEventLog = databaseQueries.gameEventsByGameId(gameId);
//        if (gameEventLog.isEmpty())
//            return Optional.empty();
//        var players = gameEventLog.get().getLineupOccurrences().stream().map(LineupOccurrence::getPlayer);
//        return Optional.of(players.map(player -> new PlayerGameStatLine(player, gameEventLog.get())).toList());
//    }

    @GetMapping("/api/stats/player-single-game-performance/{playerId}/{gameId}")
    Optional<PlayerSingleGamePerformance> playerSingleGamePerformance(@PathVariable Long playerId, @PathVariable Long gameId) {
        Optional<Player> player = databaseQueries.getPlayerById(playerId);
        if (player.isEmpty())
            return Optional.empty();
        Optional<Game> game = databaseQueries.getGameById(gameId);
        if (game.isEmpty())
            return Optional.empty();
        Optional<GameEventLog> gameEventLog = databaseQueries.gameEventsByGameId(gameId);
        if (gameEventLog.isEmpty())
            return Optional.empty();
        return Optional.of(PlayerSingleGamePerformance.builder()
                .game(game.get())
                .player(player.get())
                .performance(new Performance(player.get(), gameEventLog.get())).build());
    }

    @GetMapping("/api/stats/team-single-game-performance/{teamId}/{gameId}")
    Optional<TeamSingleGamePerformance> teamSingleGamePerformance(@PathVariable Long teamId, @PathVariable Long gameId) {
        Optional<Team> team = databaseQueries.getTeamById(teamId);
        if (team.isEmpty())
            return Optional.empty();
        Optional<Game> game = databaseQueries.getGameById(gameId);
        if (game.isEmpty())
            return Optional.empty();
        Optional<GameEventLog> gameEventLog = databaseQueries.gameEventsByGameId(gameId);
        if (gameEventLog.isEmpty())
            return Optional.empty();
        var lineup = databaseQueries.teamLineupInGame(teamId, gameId);
        return Optional.of(TeamSingleGamePerformance.builder()
                .game(game.get())
                .team(team.get())
                .performance(new Performance(lineup, gameEventLog.get())).build());
    }

    @GetMapping("/api/stats/player-over-time-performance/{playerId}/{from}/{to}")
    Optional<PlayerOverTimePerformance> playerOverTimePerformance(@PathVariable Long playerId, @PathVariable Timestamp from, @PathVariable Timestamp to) {
        Optional<Player> player = databaseQueries.getPlayerById(playerId);
        if (player.isEmpty())
            return Optional.empty();
        var games = databaseQueries.gamesStartedBetweenTimestampsAndParticipatedByPlayer(player.get(), from, to);
        if (games.isEmpty())
            return Optional.empty();
        var gameEventLogs = games.stream().map(game ->  databaseQueries.gameEventsByGameId(game.getId()))
                .filter(Optional::isPresent).map(Optional::get).toList();
        if (gameEventLogs.isEmpty())
            return Optional.empty();
        return Optional.of(PlayerOverTimePerformance.builder()
                .player(player.get())
                .from(from)
                .to(to)
                .games(games)
                .performance(new Performance(player.get(), gameEventLogs)).build());
    }

    @GetMapping("/api/stats/team-over-time-performance/{teamId}/{from}/{to}")
    Optional<TeamOverTimePerformance> teamOverTimePerformance(@PathVariable Long teamId, @PathVariable Timestamp from, @PathVariable Timestamp to) {
        Optional<Team> team = databaseQueries.getTeamById(teamId);
        if (team.isEmpty())
            return Optional.empty();
        var games = databaseQueries.gamesStartedBetweenTimestampsAndParticipatedByTeam(team.get(), from, to);
        if (games.isEmpty())
            return Optional.empty();
        var gameEventLogs = games.stream().map(game ->  databaseQueries.gameEventsByGameId(game.getId()))
                .filter(Optional::isPresent).map(Optional::get).toList();
        if (gameEventLogs.isEmpty())
            return Optional.empty();

        Set<Player> allWhoPlayedForTeamInPeriod = new HashSet<>();
        games.stream().map(game -> databaseQueries.teamLineupInGame(teamId, game.getId())).forEach(allWhoPlayedForTeamInPeriod::addAll);
        return Optional.of(TeamOverTimePerformance.builder()
                .team(team.get())
                .from(from)
                .to(to)
                .games(games)
                .performance(new Performance(allWhoPlayedForTeamInPeriod, gameEventLogs)).build());
    }

    @GetMapping("/api/stats/boxscore/{gameId}")
    Optional<List<PlayerSingleGamePerformance>> gameBoxScore(@PathVariable Long gameId) {
        var game = databaseQueries.getGameById(gameId);
        if (game.isEmpty())
            return Optional.empty();
        var gameEventLog = databaseQueries.gameEventsByGameId(gameId);
        if (gameEventLog.isEmpty())
            return Optional.empty();
        var players = gameEventLog.get().getLineupOccurrences().stream().map(LineupOccurrence::getPlayer);
        return Optional.of(players.map(player -> PlayerSingleGamePerformance.builder()
                .player(player)
                .game(game.get())
                .performance(new Performance(player, gameEventLog.get())).build()).toList());
    }
}
