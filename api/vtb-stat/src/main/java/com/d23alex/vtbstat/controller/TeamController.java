package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entity.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
public class TeamController {
    private final DatabaseQueries databaseQueries;

    public TeamController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @PostMapping("/api/teams/create_team")
    public void createTeam(@RequestBody Team team) {
        databaseQueries.saveTeam(team);
    }

    @GetMapping("/api/teams/{id}")
    public Team getTeamById(@PathVariable Long id) {
        Optional<Team> team = databaseQueries.getTeamById(id);
        return team.orElseThrow(() -> new NoSuchElementException("Команды с ID " + id + " не существует!"));
    }

    @PutMapping("/api/teams/update")
    public ResponseEntity<String> updateTeam(@RequestBody Team team) {
        try {
            databaseQueries.updateTeamById(team);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/teams/{id}")
    public ResponseEntity<String> deleteTeamById(@PathVariable Long id) {
        try {
            databaseQueries.deleteTeamById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/teams/get_all")
    public Set<Team> getAllTeams() {
        return databaseQueries.getAllTeams();
    }
}
