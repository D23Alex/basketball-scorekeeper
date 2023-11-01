package com.d23alex.vtbstat.controller;

import com.d23alex.vtbstat.db.DatabaseQueries;
import com.d23alex.vtbstat.entity.Arena;
import com.d23alex.vtbstat.entity.Coach;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class CoachController {
    private final DatabaseQueries databaseQueries;

    public CoachController(DatabaseQueries databaseQueries) {
        this.databaseQueries = databaseQueries;
    }

    @PostMapping("/api/coaches/create_coach")
    public void createCoach(@RequestBody Coach coach) {
        databaseQueries.saveCoach(coach);
    }

    @GetMapping("/api/coaches/{id}")
    public Coach getCoachById(@PathVariable Long id) {
        Optional<Coach> coach = databaseQueries.getCoachById(id);
        return coach.orElseThrow(() -> new NoSuchElementException("Тренера с ID " + id + " не существует!"));
    }



}
