package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.LeagueSchedule;
import com.d23alex.vtbstat.repository.DatabaseQueries;
import com.d23alex.vtbstat.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
public class PlayerController {
    private final DatabaseQueries databaseQueries;

    public PlayerController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @GetMapping("/api/players/by_team/{teamId}/{date}")
    Iterable<Player> playersOfTeamByDate(@PathVariable Long teamId, @PathVariable Date date) {
        return databaseQueries.teamMembersByDate(teamId, date);
    }

    @GetMapping("/api/players/by_team-season-end/{teamId}/{season}")
    Iterable<Player> playersOfTeamBySeasonEnd(@PathVariable Long teamId, @PathVariable Integer season) {
        return databaseQueries.teamMembersByDate(teamId, LeagueSchedule.seasonEnd.get(season));
    }

    @PostMapping("/api/players/create_player")
    public void createPlayer(@RequestBody Player player) {
        databaseQueries.savePlayer(player);
    }

    @GetMapping("/api/players/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        Optional<Player> player = databaseQueries.getPlayerById(id);
        return player.orElseThrow(() -> new NoSuchElementException("Игрока с ID " + id + " не существует!"));
    }

    @PutMapping("/api/players/update")
    public ResponseEntity<String> updatePlayer(@RequestBody Player player) {
        try {
            databaseQueries.updatePlayerById(player);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/players/{id}")
    public ResponseEntity<String> deletePlayerById(@PathVariable Long id) {
        try {
            databaseQueries.deletePlayerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/players/get_all")
    public Set<Player> getAllPlayers() {
        return databaseQueries.getAllPlayers();
    }
}