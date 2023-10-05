package com.d23alex.vtbstat.restapi.controllers;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entities.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {
    private final DatabaseQueries databaseQueries;

    public TeamController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @GetMapping("/api/teams")
    List<Team> getAllTeams() {
        return databaseQueries.allTeams();
    }
}
