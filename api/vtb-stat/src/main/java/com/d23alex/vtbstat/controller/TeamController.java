package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.db.repository.TeamRepository;
import com.d23alex.vtbstat.entity.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/api/teams")
    Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
