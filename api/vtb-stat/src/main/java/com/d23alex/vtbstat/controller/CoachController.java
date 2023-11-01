package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entity.Coach;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoachController {
    private final DatabaseQueries databaseQueries;

    public CoachController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @PostMapping("/api/create_coach")
    public void createCoach(@RequestBody Coach coach) {
        databaseQueries.saveCoach(coach);
    }



}
