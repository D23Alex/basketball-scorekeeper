package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.LeagueSchedule;
import com.d23alex.vtbstat.Util;
import com.d23alex.vtbstat.model.PlayerContract;
import com.d23alex.vtbstat.payload.performance.*;
import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.model.Game;
import com.d23alex.vtbstat.model.Player;
import com.d23alex.vtbstat.model.Team;
import com.d23alex.vtbstat.model.gameevents.LineupOccurrence;
import com.d23alex.vtbstat.payload.statistics.GameEventLog;
import com.d23alex.vtbstat.payload.statistics.Performance;
import com.d23alex.vtbstat.payload.statistics.PlayerGameStatLine;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
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
    Optional<PlayerOverTimePerformance> playerOverTimePerformance(@PathVariable Long playerId, @PathVariable Date from, @PathVariable Date to) {
        Optional<Player> player = databaseQueries.getPlayerById(playerId);
        if (player.isEmpty())
            return Optional.empty();
        var games = databaseQueries.gamesStartedBetweenTimestampsAndParticipatedByPlayer(player.get(), from, to);
        var gameEventLogs = games.stream().map(game ->  databaseQueries.gameEventsByGameId(game.getId()))
                .filter(Optional::isPresent).map(Optional::get).toList();
        return Optional.of(PlayerOverTimePerformance.builder()
                .player(player.get())
                .from(new Timestamp(from.getTime()))
                .to(new Timestamp(to.getTime()))
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

    @GetMapping("/api/stats/season-performance/{playerId}/{season}")
    List<PlayerInTeamOverTimePerformance> playerSeasonPerformance(@PathVariable Long playerId, @PathVariable Integer season) {
        List<PlayerContract> allContractsValidWithinSeason = databaseQueries.allPlayerContractsValidWithinTime(
                playerId, LeagueSchedule.seasonStart.get(season), LeagueSchedule.seasonEnd.get(season));

        return allContractsValidWithinSeason.stream().map(contract -> new PlayerInTeamOverTimePerformance(
                playerOverTimePerformance(playerId,
                        Util.laterDate(contract.getValidFrom(), LeagueSchedule.seasonStart.get(season)),
                        Util.earlierDate(contract.getValidTo(), LeagueSchedule.seasonEnd.get(season))).get(),
                contract.getTeam())).toList();
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

    @GetMapping("api/stats/seasons-participated/{playerId}")
    List<Integer> seasonsParticipatedByPlayer(@PathVariable Long playerId) {
        return databaseQueries.getAllPlayerContractsByPlayerId(playerId).stream()
                .map(contract -> LeagueSchedule.seasonsInRange(contract.getValidFrom(), contract.getValidTo()))
                .flatMap(Set::stream).distinct().sorted().toList();
    }
}
